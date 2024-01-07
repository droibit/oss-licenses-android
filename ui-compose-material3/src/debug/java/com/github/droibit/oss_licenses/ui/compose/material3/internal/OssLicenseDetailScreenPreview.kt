package com.github.droibit.oss_licenses.ui.compose.material3.internal

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.github.droibit.oss_licenses.parser.OssLicense

@Preview
@Composable
private fun OssLicenseDetailScreenPreview() {
  MaterialTheme {
    OssLicenseDetailScreen(
      navController = rememberNavController(),
      license = OssLicense(
        libraryName = "OSS Licenses",
        license = "https://www.apache.org/licenses/LICENSE-2.0.txt",
      ),
    )
  }
}
