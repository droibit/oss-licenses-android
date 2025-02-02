package com.github.droibit.oss_licenses.ui.wear.compose.material.internal

import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import com.github.droibit.oss_licenses.ui.compose.screenshots.TestFixtures
import com.github.droibit.oss_licenses.ui.compose.screenshots.WearDevice
import com.github.droibit.oss_licenses.ui.compose.screenshots.WearScreenshotTest
import org.junit.Test

class OssLicenseListScreenshotTest(device: WearDevice) : WearScreenshotTest(device) {
  @Test
  fun licenseListScreen() {
    val listState = ScalingLazyListState()

    runScreenshotTest(captureEnd = {
      listState.dispatchRawDelta(1000f)
    }) {
      OssLicenseListScreen(licenses = TestFixtures.licenses, listState = listState)
    }
  }
}
