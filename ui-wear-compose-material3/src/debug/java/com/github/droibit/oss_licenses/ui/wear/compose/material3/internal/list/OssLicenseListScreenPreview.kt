package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import com.github.droibit.oss_licenses.ui.OssLicenseUiState
import com.github.droibit.oss_licenses.ui.wear.compose.tooling.preview.WearPreviewDevicesJapanese

@WearPreviewDevices
@WearPreviewDevicesJapanese
@Composable
internal fun OssLicenseListScreenPreview() {
  MaterialTheme {
    OssLicenseListScreen(
      licenses = listOf(
        OssLicenseUiState("license-1", "activity-compose", ""),
        OssLicenseUiState("license-2", "compose-foundation", ""),
        OssLicenseUiState("license-3", "kotlinx-coroutines-android", ""),
        OssLicenseUiState("license-4", "wear-compose", ""),
      ),
      modifier = Modifier.fillMaxSize(),
    )
  }
}
