package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal.detail

import com.github.droibit.oss_licenses.ui.compose.screenshots.TestFixtures
import com.github.droibit.oss_licenses.ui.compose.screenshots.WearDevice
import com.github.droibit.oss_licenses.ui.compose.screenshots.WearScreenshotTest
import org.junit.Test

class OssLicenseDetailScreenshotTest(device: WearDevice) : WearScreenshotTest(device) {
  @Test
  fun licenseDetailScreen() = runScreenshotTest {
    OssLicenseDetailScreen(license = TestFixtures.aospPhotoViewer)
  }
}
