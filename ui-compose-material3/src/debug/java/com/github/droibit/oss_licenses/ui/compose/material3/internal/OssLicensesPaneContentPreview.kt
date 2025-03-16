package com.github.droibit.oss_licenses.ui.compose.material3.internal

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.github.droibit.oss_licenses.ui.OssLicenseUiState

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@PreviewScreenSizes
@Composable
internal fun OssLicensesPaneContentPreview() {
  OssLicensesTheme {
    val licenses = listOf(
      OssLicenseUiState("license-1", "activity-compose", ""),
      OssLicenseUiState("license-2", "compose-foundation", ""),
      OssLicenseUiState("license-3", "kotlinx-coroutines-android", ""),
      OssLicenseUiState("license-4", "wear-compose", ""),
      OssLicenseUiState(
        id = "license-5",
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
    val navigator = rememberListDetailPaneScaffoldNavigator<OssLicenseUiState>()
    navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, licenses.last())

    Scaffold(
      topBar = {
        OssLicensesTopAppBar(navigator)
      },
    ) { innerPadding ->
      OssLicensesPaneContent(
        licenses = licenses,
        navigator = navigator,
        modifier = Modifier.padding(innerPadding),
      )
    }
  }
}
