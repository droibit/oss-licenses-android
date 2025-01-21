package com.github.droibit.oss_licenses.ui.compose.material3.internal

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.droibit.oss_licenses.ui.compose.OssLicenseCollection
import com.github.droibit.oss_licenses.ui.navigation.compose.Routes.LicenseDetail
import com.github.droibit.oss_licenses.ui.navigation.compose.Routes.LicenseList
import com.github.droibit.oss_licenses.ui.navigation.compose.navigateToDetail
import com.github.droibit.oss_licenses.ui.viewmodel.OssLicenseViewModel

@Composable
internal fun OssLicenseNavGraph(
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController(),
  viewModel: OssLicenseViewModel = viewModel(),
) {
  LaunchedEffect(viewModel) {
    viewModel.ensureLicenses()
  }

  NavHost(
    navController = navController,
    startDestination = LicenseList.ROUTE,
    modifier = modifier,
  ) {
    composable(LicenseList.ROUTE) {
      val activity = LocalActivity.current
      val licenses by viewModel.licenses.collectAsStateWithLifecycle()
      OssLicenseListScreen(
        licenses = OssLicenseCollection(licenses),
        onNavigateBack = {
          if (!navController.popBackStack()) {
            activity?.finish()
          }
        },
        onNavigateToDetail = { license ->
          navController.navigateToDetail(license.libraryName)
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
        onNavigateBack = {
          navController.popBackStack()
        },
      )
    }
  }
}
