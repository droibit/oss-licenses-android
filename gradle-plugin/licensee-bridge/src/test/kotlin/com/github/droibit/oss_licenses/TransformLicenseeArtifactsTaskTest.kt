package com.github.droibit.oss_licenses

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.spyk
import java.io.File
import okio.Buffer
import okio.FileSystem
import okio.IOException
import okio.Path.Companion.toPath
import okio.Source
import okio.buffer
import org.gradle.api.GradleException
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.fail
import org.junit.jupiter.api.io.TempDir

@ExtendWith(MockKExtension::class)
class TransformLicenseeArtifactsTaskTest {
  @field:TempDir
  lateinit var tempDir: File

  private lateinit var fileSystem: FileSystem
  private lateinit var task: TransformLicenseeArtifactsTask

  @BeforeEach
  fun setup() {
    val project = ProjectBuilder.builder().build()
    task = project.tasks
      .register("testTransform", TransformLicenseeArtifactsTask::class.java) {
        val outputDir = tempDir.resolve("output")
        outputDir.mkdirs()
        it.outputResDir.set(outputDir)
      }.get()
    fileSystem = FileSystem.RESOURCES
  }

  @Test
  fun execute_successfullyTransformsArtifacts() {
    val inputJsonFile = tempDir.resolve("artifacts.json")
    inputJsonFile.createNewFile()
    task.inputFile.set(inputJsonFile)

    val expectedLicenses = "licenses"
    val licensesSource: Source = Buffer().apply {
      writeUtf8(expectedLicenses)
    }
    val expectedMetadata = "licensesMetadata"
    val metadataSource: Source = Buffer().apply {
      writeUtf8(expectedMetadata)
    }

    val spyTask = spyk(task)
    val licenseBundle = LicenseBundle(metadataSource, licensesSource)
    every { spyTask.transform(any()) } returns licenseBundle
    spyTask.execute()

    val rawDir = task.outputResDir
      .dir("raw")
      .get()
      .asFile
    assertThat(rawDir.exists()).isTrue()

    val outputLicensesFile = task.outputLicenses.get().asFile
    assertThat(outputLicensesFile.readText()).isEqualTo(expectedLicenses)

    val outputMetadataFile = task.outputLicensesMetadata.get().asFile
    assertThat(outputMetadataFile.readText()).isEqualTo(expectedMetadata)
  }

  @Test
  fun execute_throwsExceptionWhenInputFileDoesNotExist() {
    val nonExistentFile = tempDir.resolve("non-existent.json")
    task.inputFile.set(nonExistentFile)

    try {
      task.execute()
      fail("error!!")
    } catch (e: Exception) {
      with(assertThat(e)) {
        isInstanceOf(GradleException::class.java)
        hasMessageThat().isEqualTo("Artifacts JSON file does not exist: ${nonExistentFile.absolutePath}")
      }
    }
  }

  @Test
  fun execute_throwsExceptionWhenTransformProcessFails() {
    val inputJsonFile = tempDir.resolve("input.json")
    inputJsonFile.createNewFile()
    task.inputFile.set(inputJsonFile)

    val spyTask = spyk(task)
    val expectedException = IOException("Transform process failed")
    every { spyTask.transform(any()) } throws expectedException

    try {
      spyTask.execute()
      fail("error!!")
    } catch (e: Exception) {
      with(assertThat(e)) {
        isInstanceOf(GradleException::class.java)
        hasMessageThat().isEqualTo("Failed to transform licensee artifacts")
        hasCauseThat().isSameInstanceAs(expectedException)
      }
    }
  }

  @Test
  fun transform_successfullyTransformsArtifacts() {
    val artifactsJson = fileSystem.source("artifacts.json".toPath())
    val bundle = task.transform(artifactsJson)

    val expectedLicenseMetadata =
      """
      0:58 Mixed License Library1
      59:35 Mixed License Library1
      95:28 Mixed Unknown Library2
      124:28 Mixed Unknown Library2
      153:43 Sample Library1
      59:35 Sample Library2
      95:28 Unknown Library

      """.trimIndent()
    val actualLicenseMetadata = bundle.licensesMetadata.buffer().readByteString()
    assertThat(actualLicenseMetadata.utf8()).isEqualTo(expectedLicenseMetadata)

    val expectedLicense =
      """
      https://fedoraproject.org/wiki/Licensing/Apple_MIT_License
      https://opensource.org/license/mit/
      https://example.com/license1
      https://example.com/license2
      https://www.apache.org/licenses/LICENSE-2.0

      """.trimIndent()
    val actualLicenses = bundle.licenses.buffer().readByteString()
    assertThat(actualLicenses.utf8()).isEqualTo(expectedLicense)
  }

  @Test
  fun transform_handlesEmptyArtifactsList() {
    val emptyJson = Buffer().apply {
      writeUtf8("[]")
    }
    val bundle = task.transform(emptyJson)

    val licenseMetadata = bundle.licensesMetadata
      .buffer()
      .readByteString()
      .utf8()
    val licenses = bundle.licenses
      .buffer()
      .readByteString()
      .utf8()

    assertThat(licenseMetadata).isEmpty()
    assertThat(licenses).isEmpty()
  }
}
