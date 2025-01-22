package com.github.droibit.oss_licenses.ui.wear.compose.material.internal

import org.junit.Test

class OssLicenseListScreenshotTest(device: Device) : WearScreenshotTest(device) {
  @Test
  fun licenseListScreen() = runScreenshotTest {
    OssLicenseListScreen(licenses = TestFixtures.licenses)
  }
}
