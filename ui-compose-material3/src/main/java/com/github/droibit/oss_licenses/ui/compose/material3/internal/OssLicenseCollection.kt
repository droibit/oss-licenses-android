package com.github.droibit.oss_licenses.ui.compose.material3.internal

import androidx.compose.runtime.Immutable
import com.github.droibit.oss_licenses.parser.OssLicense

@Immutable
data class OssLicenseCollection(
  val value: List<OssLicense> = emptyList(),
) {
  operator fun invoke(): List<OssLicense> = value
}
