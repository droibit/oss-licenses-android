package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material3.AppScaffold
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.github.droibit.oss_licenses.ui.viewmodel.OssLicenseViewModel
import com.github.droibit.oss_licenses.ui.wear.compose.material3.internal.detail.OssLicenseDetailScreen
import com.github.droibit.oss_licenses.ui.wear.compose.material3.internal.list.OssLicenseListScreen
import com.github.droibit.oss_licenses.ui.wear.compose.navigation.Routes.LicenseDetail
import com.github.droibit.oss_licenses.ui.wear.compose.navigation.Routes.LicenseList
import com.github.droibit.oss_licenses.ui.wear.compose.navigation.navigateToDetail

@Composable
internal fun OssLicensesApp(
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberSwipeDismissableNavController(),
  viewModel: OssLicenseViewModel = viewModel(),
) {
  val context = LocalContext.current
  LaunchedEffect(viewModel) {
    viewModel.loadLicenses(context)
  }

  AppScaffold(
    // Disable time display.
    timeText = {},
  ) {
    SwipeDismissableNavHost(
      navController = navController,
      startDestination = LicenseList.ROUTE,
      modifier = modifier,
    ) {
      composable(LicenseList.ROUTE) {
        val licenses by viewModel.licenses.collectAsStateWithLifecycle()
        OssLicenseListScreen(
          licenses = licenses,
          onNavigateToDetail = { license ->
            navController.navigateToDetail(license.library)
          },
        )
      }

      composable(
        route = LicenseDetail.ROUTE,
        arguments = LicenseDetail.arguments,
      ) {
        val libraryName = LicenseDetail.getLibraryName(requireNotNull(it.arguments))
        OssLicenseDetailScreen(
          license = viewModel.getLicense(libraryName),
        )
      }
    }
  }
}
