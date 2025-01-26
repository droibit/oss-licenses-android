package com.github.droibit.oss_licenses.ui.compose.material3.internal

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.droibit.oss_licenses.parser.OssLicense

@Preview
@Composable
private fun OssLicenseListScreenPreview() {
  MaterialTheme {
    OssLicenseListScreen(
      licenses = listOf(
        OssLicense("activity-compose", ""),
        OssLicense("compose-foundation", ""),
        OssLicense("kotlinx-coroutines-android", ""),
        OssLicense("wear-compose", ""),
      ),
    )
  }
}
