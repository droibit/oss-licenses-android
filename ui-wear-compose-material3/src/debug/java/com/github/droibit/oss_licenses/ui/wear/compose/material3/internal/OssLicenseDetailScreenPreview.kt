package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal

import androidx.compose.runtime.Composable
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import com.github.droibit.oss_licenses.parser.OssLicense

@WearPreviewDevices
@Composable
private fun OssLicenseDetailScreenPreview() {
  MaterialTheme {
    OssLicenseDetailScreen(
      license = OssLicense(
        libraryName = "OSS Licenses",
        license = "https://www.apache.org/licenses/LICENSE-2.0.txt",
      ),
    )
  }
}
