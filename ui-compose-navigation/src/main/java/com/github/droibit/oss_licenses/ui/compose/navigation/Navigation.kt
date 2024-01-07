package com.github.droibit.oss_licenses.ui.compose.navigation

import androidx.annotation.RestrictTo
import androidx.navigation.NavController
import com.github.droibit.oss_licenses.ui.compose.navigation.Routes.LicenseDetail.toDetail
import com.github.droibit.oss_licenses.ui.compose.navigation.Routes.LicenseList

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun NavController.navigateToDetail(libraryName: String) {
  if (currentDestination?.route == LicenseList.ROUTE) {
    navigate(toDetail(libraryName))
  }
}
