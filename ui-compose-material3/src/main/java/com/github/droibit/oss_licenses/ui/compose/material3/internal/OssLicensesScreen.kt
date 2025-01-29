package com.github.droibit.oss_licenses.ui.compose.material3.internal

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue.Companion.Hidden
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.viewmodel.OssLicenseViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
internal fun OssLicensesScreen(
  modifier: Modifier = Modifier,
  viewModel: OssLicenseViewModel = viewModel(),
) {
  val context = LocalContext.current
  LaunchedEffect(viewModel) {
    viewModel.loadLicenses(context)
  }

  val activity = LocalActivity.current
  val navigator = rememberListDetailPaneScaffoldNavigator<OssLicense>()
  val navigateBack: () -> Unit = {
    if (navigator.canNavigateBack()) {
      navigator.navigateBack()
    } else {
      activity?.finish()
    }
  }
  BackHandler(navigator.canNavigateBack(), onBack = navigateBack)

  Scaffold(
    modifier = modifier,
    topBar = {
      OssLicensesTopAppBar(
        navigator = navigator,
        onBack = navigateBack,
      )
    },
  ) { innerPadding ->
    val licenses by viewModel.licenses.collectAsStateWithLifecycle()
    OssLicensesPaneContent(
      licenses = licenses,
      navigator = navigator,
      modifier = Modifier.padding(innerPadding),
    )
  }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
internal fun ThreePaneScaffoldNavigator<*>.isSinglePane(): Boolean {
  return scaffoldValue.primary == Hidden || scaffoldValue.secondary == Hidden
}
