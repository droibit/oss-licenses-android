package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal.detail

import androidx.wear.compose.foundation.lazy.TransformingLazyColumnState
import com.github.droibit.oss_licenses.ui.compose.screenshots.TestFixtures
import com.github.droibit.oss_licenses.ui.compose.screenshots.WearDevice
import com.github.droibit.oss_licenses.ui.compose.screenshots.WearScreenshotTest
import org.junit.Test

class OssLicenseDetailScreenshotTest(device: WearDevice) : WearScreenshotTest(device) {
  @Test
  fun licenseDetailScreen() {
    val listState = TransformingLazyColumnState()

    runScreenshotTest(
      captureEnd = {
        listState.dispatchRawDelta(500f)
      },
    ) {
      OssLicenseDetailScreen(license = TestFixtures.aospPhotoViewer, listState = listState)
    }
  }
}
