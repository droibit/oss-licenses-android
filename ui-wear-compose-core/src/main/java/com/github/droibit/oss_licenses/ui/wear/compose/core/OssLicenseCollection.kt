package com.github.droibit.oss_licenses.ui.wear.compose.core

import androidx.annotation.RestrictTo
import androidx.compose.runtime.Immutable
import com.github.droibit.oss_licenses.parser.OssLicense

/**
 * A collection of [OssLicense].
 *
 * Marked with @Immutable, this class can be used in Compose without causing unnecessary recompositions,
 * enhancing your Compose UI's stability and performance.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
@Immutable
data class OssLicenseCollection(
  val value: List<OssLicense> = emptyList(),
) {
  operator fun invoke(): List<OssLicense> = value
}
