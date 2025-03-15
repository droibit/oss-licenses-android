package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal.list

import androidx.wear.compose.foundation.lazy.TransformingLazyColumnState
import com.github.droibit.oss_licenses.ui.compose.screenshots.TestFixtures.licenses
import com.github.droibit.oss_licenses.ui.compose.screenshots.WearDevice
import com.github.droibit.oss_licenses.ui.compose.screenshots.WearScreenshotTest
import org.junit.Test

class OssLicenseListScreenshotTest(device: WearDevice) : WearScreenshotTest(device) {
  @Test
  fun licenseListScreen() {
    val listState = TransformingLazyColumnState()

    runScreenshotTest(
      captureEnd = {
        listState.dispatchRawDelta(1000f)
      },
    ) {
      OssLicenseListScreen(
        licenses = licenses,
        listState = listState,
      )
    }
  }
}
