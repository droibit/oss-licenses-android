package com.github.droibit.oss_licenses.parser

import java.io.Serializable

/**
 * Represents a third-party library and its license.
 *
 * @param libraryName The name of the third-party library.
 * @param license The license of the third-party library.
 */
data class OssLicense(
  val libraryName: String,
  val license: String,
) : Serializable
