package com.github.droibit.oss_licenses.ui.wear.compose.internal

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.rememberScalingLazyListState
import com.github.droibit.oss_licenses.parser.OssLicense

@Preview(
  device = Devices.WEAR_OS_LARGE_ROUND,
  showSystemUi = true,
  backgroundColor = 0xff000000,
  showBackground = true,
  group = "Devices - Large Round",
)
@Preview(
  device = Devices.WEAR_OS_SQUARE,
  showSystemUi = true,
  backgroundColor = 0xff000000,
  showBackground = true,
  group = "Devices - Square",
)
@Composable
fun OssLicenseListPreview() {
  MaterialTheme {
    Scaffold {
      OssLicenseList(
        licenses = listOf(
          OssLicense("activity-compose", ""),
          OssLicense("compose-foundation", ""),
          OssLicense("kotlinx-coroutines-android", ""),
          OssLicense("wear-compose", ""),
        ),
        modifier = Modifier.fillMaxSize(),
        listState = rememberScalingLazyListState(),
        onItemClick = {},
      )
    }
  }
}
