package com.github.droibit.oss_licenses.ui.navigation.compose

import androidx.annotation.RestrictTo
import androidx.navigation.NavController
import com.github.droibit.oss_licenses.ui.navigation.compose.Routes.LicenseDetail.toDetail
import com.github.droibit.oss_licenses.ui.navigation.compose.Routes.LicenseList

/**
 * Navigates to the license detail screen.
 *
 * @param libraryName The library name to show the detail.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun NavController.navigateToDetail(libraryName: String) {
  if (currentDestination?.route == LicenseList.ROUTE) {
    navigate(toDetail(libraryName))
  }
}
