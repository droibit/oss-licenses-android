package com.github.droibit.oss_licenses.ui.compose.material3.internal

import android.util.Base64
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.droibit.oss_licenses.ui.compose.material3.internal.OssLicenseNavGraph.KEY_LIBRARY_NAME
import com.github.droibit.oss_licenses.ui.compose.material3.internal.OssLicenseNavGraph.ROUTE_DETAIL
import com.github.droibit.oss_licenses.ui.compose.material3.internal.OssLicenseNavGraph.ROUTE_LIST
import com.github.droibit.oss_licenses.ui.viewmodel.OssLicenseViewModel

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
  viewModel: OssLicenseViewModel,
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController(),
) {
  NavHost(
    navController = navController,
    startDestination = ROUTE_LIST,
    modifier = modifier,
  ) {
    composable(ROUTE_LIST) {
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
        navController = navController,
        license = viewModel.getLicense(String(libraryName)),
      )
    }
  }
}
