package com.github.droibit.oss_licenses

import app.cash.licensee.ArtifactDetail
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.okio.decodeFromBufferedSource
import okio.Buffer
import okio.FileSystem
import okio.IOException
import okio.Path.Companion.toOkioPath
import okio.Source
import okio.buffer
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFile
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.jetbrains.annotations.VisibleForTesting

private const val RAW_DIR = "raw"

@Suppress("LeakingThis")
@CacheableTask
abstract class TransformLicenseeArtifactsTask : DefaultTask() {
  @get:InputFile
  @get:PathSensitive(PathSensitivity.RELATIVE)
  internal abstract val inputFile: RegularFileProperty

  @get:OutputDirectory
  internal abstract val outputResDir: DirectoryProperty

  @get:Internal
  val outputLicenses: Provider<RegularFile> = outputResDir.file("$RAW_DIR/third_party_licenses")

  @get:Internal
  val outputLicensesMetadata: Provider<RegularFile> =
    outputResDir.file("$RAW_DIR/third_party_license_metadata")

  @TaskAction
  fun execute() {
    val artifactsJsonFile = inputFile.asFile.get()
    if (!artifactsJsonFile.exists()) {
      throw GradleException("Artifacts JSON file does not exist: ${artifactsJsonFile.absolutePath}")
    }

    val rawDir = outputResDir.dir(RAW_DIR).get().asFile
    rawDir.mkdirs()

    try {
      val fileSystem = FileSystem.SYSTEM
      val bundle = transform(fileSystem.source(artifactsJsonFile.toOkioPath()))
      fileSystem.write(bundle.licenses, outputLicenses)
      fileSystem.write(bundle.licensesMetadata, outputLicensesMetadata)
    } catch (e: IOException) {
      throw GradleException("Failed to transform licensee artifacts", e)
    }
  }

  @VisibleForTesting
  @Throws(IOException::class)
  @OptIn(ExperimentalSerializationApi::class)
  internal fun transform(artifactsJson: Source): LicenseBundle {
    val artifactDetails = Json.decodeFromBufferedSource<List<ArtifactDetail>>(
      artifactsJson.buffer(),
    )

    val artifacts = sortedSetOf(
      compareBy(String.CASE_INSENSITIVE_ORDER, Artifact::name)
        .then(compareBy(String.CASE_INSENSITIVE_ORDER, Artifact::license)),
    )
    for (artifact in artifactDetails) {
      val artifactName = artifact.name ?: artifact.artifactId
      for (license in artifact.spdxLicenses) {
        artifacts += Artifact(
          name = artifactName,
          license = license.url,
        )
      }
      for (license in artifact.unknownLicenses) {
        license.url?.let { licenseUrl ->
          artifacts += Artifact(
            name = artifactName,
            license = licenseUrl,
          )
        }
      }
    }
    return createLicenseBundle(artifacts)
  }

  private fun createLicenseBundle(artifacts: Set<Artifact>): LicenseBundle {
    val licenses = Buffer()
    val licensesMetadata = Buffer()
    val licenseOffsetRanges = mutableMapOf<String, LongRange>()
    for (artifact in artifacts) {
      val existingOffsetRange = licenseOffsetRanges[artifact.license]
      if (existingOffsetRange == null) {
        val newOffsetRange = licenses.writeLicense(artifact.license)
        licenseOffsetRanges[artifact.license] = newOffsetRange
        licensesMetadata.writeLicenseEntry(newOffsetRange, artifact.name)
      } else {
        licensesMetadata.writeLicenseEntry(existingOffsetRange, artifact.name)
      }
    }
    return LicenseBundle(
      licensesMetadata = licensesMetadata,
      licenses = licenses,
    )
  }

  private fun Buffer.writeLicenseEntry(offsetRange: LongRange, name: String) {
    writeUtf8("${offsetRange.first}:${offsetRange.last} $name\n")
  }

  private fun Buffer.writeLicense(license: String): LongRange {
    val startIndex = size
    writeUtf8(license)
    val endIndex = size - startIndex
    writeUtf8("\n")
    return startIndex..endIndex
  }

  private fun FileSystem.write(source: Source, file: Provider<RegularFile>) {
    write(file.get().asFile.toOkioPath()) {
      writeAll(source)
    }
  }
}

internal class LicenseBundle(
  val licensesMetadata: Source,
  val licenses: Source,
)

private data class Artifact(
  val name: String,
  val license: String,
)
