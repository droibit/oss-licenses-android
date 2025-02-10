package com.github.droibit.oss_licenses.ui.compose.material3.internal

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.droibit.oss_licenses.parser.OssLicense

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
internal fun OssLicensesPaneContent(
  licenses: List<OssLicense>,
  modifier: Modifier = Modifier,
  navigator: ThreePaneScaffoldNavigator<OssLicense> =
    rememberListDetailPaneScaffoldNavigator<OssLicense>(),
) {
  ListDetailPaneScaffold(
    directive = navigator.scaffoldDirective,
    value = navigator.scaffoldValue,
    modifier = modifier,
    listPane = {
      AnimatedPane {
        OssLicenseList(
          licenses = licenses,
          onItemClick = { license ->
            navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, license)
          },
        )
      }
    },
    detailPane = {
      AnimatedPane {
        navigator.currentDestination?.content?.let { license ->
          OssLicenseDetail(
            license = license,
            showLibraryName = !navigator.isSinglePane(),
          )
        }
      }
    },
  )
}
