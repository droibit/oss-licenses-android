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
        "license-5",
        "The MIT License",
        """
        Copyright <YEAR> <COPYRIGHT HOLDER>

        Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

        The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
