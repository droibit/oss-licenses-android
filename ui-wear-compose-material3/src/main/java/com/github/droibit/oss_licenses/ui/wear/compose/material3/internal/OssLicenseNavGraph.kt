package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material3.AppScaffold
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.github.droibit.oss_licenses.ui.compose.OssLicenseCollection
import com.github.droibit.oss_licenses.ui.navigation.compose.Routes.LicenseDetail
import com.github.droibit.oss_licenses.ui.navigation.compose.Routes.LicenseList
import com.github.droibit.oss_licenses.ui.navigation.compose.navigateToDetail
import com.github.droibit.oss_licenses.ui.viewmodel.OssLicenseViewModel

@Composable
internal fun OssLicenseNavGraph(
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberSwipeDismissableNavController(),
  viewModel: OssLicenseViewModel = viewModel(),
) {
  LaunchedEffect(viewModel) {
    viewModel.ensureLicenses()
  }

  AppScaffold {
    SwipeDismissableNavHost(
      navController = navController,
      startDestination = LicenseList.ROUTE,
      modifier = modifier,
    ) {
      composable(LicenseList.ROUTE) {
        val licenses by viewModel.licenses.collectAsStateWithLifecycle()
        OssLicenseListScreen(
          licenses = OssLicenseCollection(licenses),
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
        )
      }
    }
  }
}
