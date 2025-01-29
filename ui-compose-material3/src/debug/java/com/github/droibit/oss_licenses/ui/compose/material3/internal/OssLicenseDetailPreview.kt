package com.github.droibit.oss_licenses.ui.compose.material3.internal

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.github.droibit.oss_licenses.parser.OssLicense

@PreviewLightDark
@Composable
private fun OssLicenseDetailPreview() {
  OssLicensesTheme {
    Scaffold { innerPadding ->
      OssLicenseDetail(
        license = OssLicense(
          libraryName = "OSS Licenses",
          license = "https://www.apache.org/licenses/LICENSE-2.0.txt",
        ),
        modifier = Modifier.padding(innerPadding),
      )
    }
  }
}

@PreviewLightDark
@Composable
private fun OssLicenseTextPreview() {
  OssLicensesTheme {
    Scaffold { innerPadding ->
      OssLicenseDetail(
        license = OssLicense(
          libraryName = "OSS Licenses",
          license = "https://www.apache.org/licenses/LICENSE-2.0.txt",
        ),
        showLibraryName = false,
        modifier = Modifier.padding(innerPadding),
      )
    }
  }
}
