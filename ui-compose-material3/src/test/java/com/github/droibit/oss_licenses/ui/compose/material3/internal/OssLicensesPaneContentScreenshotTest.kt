package com.github.droibit.oss_licenses.ui.compose.material3.internal

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.ui.Modifier
import com.github.droibit.oss_licenses.ui.OssLicenseUiState
import com.github.droibit.oss_licenses.ui.compose.screenshots.Device
import com.github.droibit.oss_licenses.ui.compose.screenshots.ScreenshotTest
import com.github.droibit.oss_licenses.ui.compose.screenshots.TestFixtures
import org.junit.Test

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
class OssLicensesPaneContentScreenshotTest(device: Device) : ScreenshotTest(device) {
  @Test
  fun paneContent() = runScreenshotTest {
    OssLicensesTheme {
      val licenses = TestFixtures.licenses

      val navigator = rememberListDetailPaneScaffoldNavigator<OssLicenseUiState>()
      if (device in setOf(Device.PhoneDark, Device.Tablet, Device.TabletLargeFonts)) {
        navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, TestFixtures.kotlinCoroutines)
      }

      Scaffold(
        topBar = {
          OssLicensesTopAppBar(navigator)
        },
      ) { innerPadding ->
        OssLicensesPaneContent(
          licenses = licenses,
          navigator = navigator,
          modifier = Modifier.padding(innerPadding),
        )
      }
    }
  }
}
