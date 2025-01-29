package com.github.droibit.oss_licenses.ui.compose.material3.internal

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.github.droibit.oss_licenses.parser.OssLicense

@PreviewLightDark
@Composable
private fun OssLicenseListPreview() {
  OssLicensesTheme {
    Scaffold { innerPadding ->
      OssLicenseList(
        licenses = listOf(
          OssLicense("activity-compose", ""),
          OssLicense("compose-foundation", ""),
          OssLicense("kotlinx-coroutines-android", ""),
          OssLicense("wear-compose", ""),
        ),
        modifier = Modifier.padding(innerPadding),
      )
    }
  }
}
