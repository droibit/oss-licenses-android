package com.github.droibit.oss_licenses.ui.compose.screenshots

import com.github.droibit.oss_licenses.parser.OssLicense

@Suppress("MemberVisibilityCanBePrivate")
object TestFixtures {
  val aospPhotoViewer = OssLicense(
    "AOSP PhotoViewer",
    "http://www.apache.org/licenses/LICENSE-2.0",
  )

  val abseil = OssLicense(
    "Abseil",
    "http://www.apache.org/licenses/LICENSE-2.0",
  )

  val androidDeviceOriginLibrary = OssLicense(
    "Android Device Origin Library",
    "http://www.apache.org/licenses/LICENSE-2.0",
  )

  val licenses: List<OssLicense> = listOf(
    aospPhotoViewer,
    abseil,
    androidDeviceOriginLibrary,
  )
}
