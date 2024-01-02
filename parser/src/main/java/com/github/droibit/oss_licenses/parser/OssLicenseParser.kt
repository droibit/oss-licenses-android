package com.github.droibit.oss_licenses.parser

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.RestrictTo
import androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP
import java.io.IOException
import kotlin.coroutines.suspendCoroutine
import okio.Source
import okio.buffer
import okio.source

private const val RES_LICENSES_METADATA = "third_party_license_metadata"
private const val RES_LICENSES = "third_party_licenses"

@RestrictTo(LIBRARY_GROUP)
class OssLicenseParser(
  private val context: Context,
) {

  @SuppressLint("DiscouragedApi")
  @Throws(IOException::class)
  suspend fun parse(ignoreLibraries: Set<String> = emptySet()): List<OssLicense> {
    val appContext = context.applicationContext
    val res = appContext.resources

    val licensesMetaDataResId =
      res.getIdentifier(RES_LICENSES_METADATA, "raw", appContext.packageName)
    val licensesResId =
      res.getIdentifier(RES_LICENSES, "raw", appContext.packageName)

    check(licensesResId != 0 || licensesMetaDataResId != 0) {
      "Third party library license resources generated by OSS Licenses Gradle Plugin dose not exist."
    }

    return suspendCoroutine {
      val result: Result<List<OssLicense>> = try {
        val ossLicenses = parse(
          srcLicenses = res.openRawResource(licensesResId).source(),
          srcLicensesMetadata = res.openRawResource(licensesMetaDataResId).source(),
          ignoreLibraries = ignoreLibraries,
        )
        Result.success(ossLicenses)
      } catch (e: IOException) {
        Result.failure(e)
      }
      it.resumeWith(result)
    }
  }

  internal fun parse(
    srcLicenses: Source,
    srcLicensesMetadata: Source,
    ignoreLibraries: Set<String>,
  ): List<OssLicense> {
    val licenseMetadata = srcLicensesMetadata.buffer()
      .use {
        buildList {
          while (true) {
            val line = it.readUtf8Line() ?: break
            val name = line.substringAfter(" ")
            if (name in ignoreLibraries) {
              continue
            }
            val licenseByteRange = line.substringBefore(" ").split(":")
            val metadata = OssLicenseMetadata(
              name = name,
              beginIndex = licenseByteRange[0].toInt(),
              byteCount = licenseByteRange[1].toInt(),
            )
            add(metadata)
          }
        }
      }
    val licenses = srcLicenses.buffer().use {
      it.readByteString()
    }

    return licenseMetadata
      .map { metadata ->
        val license = licenses.substring(
          metadata.beginIndex,
          metadata.beginIndex + metadata.byteCount,
        )
        OssLicense(
          libraryName = metadata.name,
          license = license.utf8(),
        )
      }
      .distinct()
      .sortedBy { it.libraryName.uppercase() }
  }
}
