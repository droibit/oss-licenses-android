package com.github.droibit.oss_licenses.ui.wear.compose.material.internal

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.github.droibit.oss_licenses.ui.compose.navigation.Routes.LicenseDetail
import com.github.droibit.oss_licenses.ui.compose.navigation.Routes.LicenseList
import com.github.droibit.oss_licenses.ui.viewmodel.OssLicenseViewModel

@Composable
internal fun OssLicenseNavGraph(
  viewModel: OssLicenseViewModel,
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberSwipeDismissableNavController(),
) {
  SwipeDismissableNavHost(
    navController = navController,
    startDestination = LicenseList.ROUTE,
    modifier = modifier,
  ) {
    composable(LicenseList.ROUTE) {
      OssLicenseListScreen(
        navController = navController,
        viewModel = viewModel,
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
