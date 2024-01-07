package com.github.droibit.oss_licenses.ui.compose.material3.internal

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.droibit.oss_licenses.ui.compose.navigation.Routes.LicenseDetail
import com.github.droibit.oss_licenses.ui.compose.navigation.Routes.LicenseList
import com.github.droibit.oss_licenses.ui.viewmodel.OssLicenseViewModel

@Composable
internal fun OssLicenseNavGraph(
  viewModel: OssLicenseViewModel,
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController(),
) {
  NavHost(
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
        navController = navController,
        license = viewModel.getLicense(libraryName),
      )
    }
  }
}
