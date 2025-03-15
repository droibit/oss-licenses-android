package com.github.droibit.oss_licenses.ui.wear.compose.navigation

import android.os.Bundle
import android.util.Base64
import androidx.annotation.RestrictTo
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
object Routes {
  /**
   * Route for the license list screen.
   */
  object LicenseList {
    const val ROUTE = "license_list"
  }

  /**
   * Route for the license detail screen.
   */
  object LicenseDetail {
    private const val PATH = "license_detail"
    private const val KEY_LICENSE_ID = "license_id"
    const val ROUTE = "$PATH?$KEY_LICENSE_ID={$KEY_LICENSE_ID}"

    val arguments: List<NamedNavArgument>
      get() = listOf(
        navArgument(KEY_LICENSE_ID) {
          type = NavType.StringType
        },
      )

    /**
     * Creates a deep link to the license detail screen.
     */
    fun toDetail(licenseId: String): String {
      val encoded = Base64.encodeToString(
        licenseId.toByteArray(),
        Base64.URL_SAFE or Base64.NO_WRAP,
      )
      return "$PATH?$KEY_LICENSE_ID=$encoded"
    }

    /**
     * Extracts the license id from the [Bundle] arguments.
     */
    fun getLicenseId(args: Bundle): String {
      val licenseId = Base64.decode(
        requireNotNull(args.getString(KEY_LICENSE_ID)),
        Base64.URL_SAFE or Base64.NO_WRAP,
      )
      return String(licenseId)
    }
  }
}
