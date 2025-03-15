package com.github.droibit.oss_licenses.ui.wear.compose.navigation

import androidx.annotation.RestrictTo
import androidx.navigation.NavController
import com.github.droibit.oss_licenses.ui.wear.compose.navigation.Routes.LicenseDetail.toDetail
import com.github.droibit.oss_licenses.ui.wear.compose.navigation.Routes.LicenseList

/**
 * Navigates to the license detail screen.
 *
 * @param licenseId The id of the license to navigate to.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun NavController.navigateToDetail(licenseId: String) {
  if (currentDestination?.route == LicenseList.ROUTE) {
    navigate(toDetail(licenseId))
  }
}
