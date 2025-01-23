package com.github.droibit.oss_licenses.parser.internal

/**
 * Contains metadata for a single license segment, including the library name
 * and the position information within the license text file.
 *
 * @property name The name of the third-party library.
 * @property beginIndex The start index of this library’s license text in the file.
 * @property byteCount The length in bytes of this library’s license text.
 */
internal data class OssLicenseMetadata(
  val name: String,
  val beginIndex: Int,
  val byteCount: Int,
) {
  /**
   * The end index of this library’s license text in the file.
   */
  val endIndex: Int get() = beginIndex + byteCount
}
