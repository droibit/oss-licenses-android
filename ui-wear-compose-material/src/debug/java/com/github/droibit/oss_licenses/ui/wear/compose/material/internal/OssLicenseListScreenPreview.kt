package com.github.droibit.oss_licenses.ui.wear.compose.material.internal

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import androidx.wear.tooling.preview.devices.WearDevices
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.compose.OssLicenseCollection

@WearPreviewDevices
@Preview(
  device = WearDevices.SMALL_ROUND,
  backgroundColor = 0xff000000,
  showBackground = true,
  group = "Devices - Small Round(Japanese)",
  locale = "ja",
  showSystemUi = true,
)
@Composable
private fun OssLicenseListScreenPreview() {
  MaterialTheme {
    OssLicenseListScreen(
      licenses = OssLicenseCollection(
        listOf(
          OssLicense("activity-compose", ""),
          OssLicense("compose-foundation", ""),
          OssLicense("kotlinx-coroutines-android", ""),
          OssLicense("wear-compose", ""),
        ),
      ),
    )
  }
}
