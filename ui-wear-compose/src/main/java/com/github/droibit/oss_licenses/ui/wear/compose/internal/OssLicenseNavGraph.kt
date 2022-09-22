package com.github.droibit.oss_licenses.ui.wear.compose.internal

import android.util.Base64
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.github.droibit.oss_licenses.ui.viewmodel.OssLicenseViewModel
import com.github.droibit.oss_licenses.ui.wear.compose.internal.OssLicenseNavGraph.KEY_LIBRARY_NAME
import com.github.droibit.oss_licenses.ui.wear.compose.internal.OssLicenseNavGraph.ROUTE_DETAIL
import com.github.droibit.oss_licenses.ui.wear.compose.internal.OssLicenseNavGraph.ROUTE_LIST

internal object OssLicenseNavGraph {
  const val ROUTE_LIST = "license_list"
  const val ROUTE_DETAIL = "license_detail"

  const val KEY_LIBRARY_NAME = "library_name"

  object Directions {
    fun toDetail(libraryName: String): String {
      val encoded = Base64.encodeToString(
        libraryName.toByteArray(),
        Base64.URL_SAFE or Base64.NO_WRAP,
      )
      return "$ROUTE_DETAIL?$KEY_LIBRARY_NAME=$encoded"
    }
  }
}

@Composable
internal fun OssLicenseNavGraph(
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberSwipeDismissableNavController(),
  viewModel: OssLicenseViewModel,
) {
  SwipeDismissableNavHost(
    navController = navController,
    startDestination = ROUTE_LIST,
    modifier = modifier,
  ) {
    composable(route = ROUTE_LIST) {
      OssLicenseListScreen(
        navController = navController,
        viewModel = viewModel,
      )
    }

    composable(
      route = "$ROUTE_DETAIL?$KEY_LIBRARY_NAME={$KEY_LIBRARY_NAME}",
      arguments = listOf(
        navArgument(KEY_LIBRARY_NAME) {
          type = NavType.StringType
        },
      ),
    ) {
      val args = requireNotNull(it.arguments)
      val libraryName = Base64.decode(
        requireNotNull(args.getString(KEY_LIBRARY_NAME)),
        Base64.URL_SAFE or Base64.NO_WRAP,
      )
      OssLicenseDetailScreen(
        license = viewModel.getLicense(String(libraryName)),
      )
    }
  }
}
