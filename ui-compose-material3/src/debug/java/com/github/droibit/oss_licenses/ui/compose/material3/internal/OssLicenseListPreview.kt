package com.github.droibit.oss_licenses.ui.compose.material3.internal

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.github.droibit.oss_licenses.ui.OssLicenseUiState

@PreviewLightDark
@Composable
internal fun OssLicenseListPreview() {
  OssLicensesTheme {
    Scaffold { innerPadding ->
      OssLicenseList(
        licenses = listOf(
          OssLicenseUiState("license-1", "activity-compose", ""),
          OssLicenseUiState("license-2", "compose-foundation", ""),
          OssLicenseUiState("license-3", "kotlinx-coroutines-android", ""),
          OssLicenseUiState("license-4", "wear-compose", ""),
        ),
        modifier = Modifier.padding(innerPadding),
      )
    }
  }
}
