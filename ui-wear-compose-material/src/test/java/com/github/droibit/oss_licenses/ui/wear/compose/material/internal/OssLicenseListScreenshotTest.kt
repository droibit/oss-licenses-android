package com.github.droibit.oss_licenses.ui.wear.compose.material.internal

import com.github.droibit.oss_licenses.ui.compose.screenshots.TestFixtures
import com.github.droibit.oss_licenses.ui.compose.screenshots.WearDevice
import com.github.droibit.oss_licenses.ui.compose.screenshots.WearScreenshotTest
import org.junit.Test

class OssLicenseListScreenshotTest(device: WearDevice) : WearScreenshotTest(device) {
  @Test
  fun licenseListScreen() = runScreenshotTest {
    OssLicenseListScreen(licenses = TestFixtures.licenses)
  }
}
