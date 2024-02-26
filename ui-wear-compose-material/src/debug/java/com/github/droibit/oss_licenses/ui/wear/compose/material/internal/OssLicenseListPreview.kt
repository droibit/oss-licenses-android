package com.github.droibit.oss_licenses.ui.wear.compose.material.internal

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import com.github.droibit.oss_licenses.parser.OssLicense

@WearPreviewDevices
@Composable
private fun OssLicenseListPreview() {
  MaterialTheme {
    Scaffold {
      OssLicenseListImpl(
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
