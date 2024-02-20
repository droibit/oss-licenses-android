package com.github.droibit.oss_licenses.parser

import androidx.annotation.RestrictTo
import java.io.Serializable

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
data class OssLicense(
  val libraryName: String,
  val license: String,
) : Serializable

internal data class OssLicenseMetadata(
  val name: String,
  val beginIndex: Int,
  val byteCount: Int,
) {
  val endIndex: Int get() = beginIndex + byteCount
}
