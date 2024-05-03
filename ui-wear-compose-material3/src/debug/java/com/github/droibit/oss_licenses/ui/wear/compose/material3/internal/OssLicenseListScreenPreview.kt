package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.compose.OssLicenseCollection

@WearPreviewDevices
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
      modifier = Modifier.fillMaxSize(),
    )
  }
}
