package com.github.droibit.oss_licenses.ui.wear.compose.material.internal

import org.junit.Test

class OssLicenseDetailScreenshotTest(device: Device) : WearScreenshotTest(device) {
  @Test
  fun licenseDetailScreen() = runScreenshotTest {
    OssLicenseDetailScreen(license = TestFixtures.aospPhotoViewer)
  }
}
