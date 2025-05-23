package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal.detail

import androidx.compose.runtime.Composable
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import com.github.droibit.oss_licenses.ui.OssLicenseUiState

@WearPreviewDevices
@Composable
internal fun OssLicenseDetailScreenShortLicensePreview() {
  MaterialTheme {
    OssLicenseDetailScreen(
      license = OssLicenseUiState(
        id = "license",
        library = "OSS Licenses",
        licenseText = "https://www.apache.org/licenses/LICENSE-2.0.txt",
      ),
    )
  }
}

@WearPreviewDevices
@Composable
internal fun OssLicenseDetailScreenLongLicensePreview() {
  MaterialTheme {
    OssLicenseDetailScreen(
      license = OssLicenseUiState(
        id = "license",
        library = "OSS Licenses Android",
        licenseText =
          """
          Copyright (C) 2018 Shinya Kumagai

          Licensed under the Apache License, Version 2.0 (the "License");
          you may not use this file except in compliance with the License.
          You may obtain a copy of the License at

              http://www.apache.org/licenses/LICENSE-2.0

          Unless required by applicable law or agreed to in writing, software
          distributed under the License is distributed on an "AS IS" BASIS,
          WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
          See the License for the specific language governing permissions and
          limitations under the License.
          """.trimIndent(),
      ),
    )
  }
}
