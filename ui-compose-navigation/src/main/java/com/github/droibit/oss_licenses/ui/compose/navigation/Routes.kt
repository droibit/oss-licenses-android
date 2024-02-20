package com.github.droibit.oss_licenses.ui.compose.navigation

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
    private const val KEY_LIBRARY_NAME = "library_name"
    const val ROUTE = "$PATH?$KEY_LIBRARY_NAME={$KEY_LIBRARY_NAME}"

    val arguments: List<NamedNavArgument>
      get() = listOf(
        navArgument(KEY_LIBRARY_NAME) {
          type = NavType.StringType
        },
      )

    /**
     * Creates a deep link to the license detail screen.
     */
    fun toDetail(libraryName: String): String {
      val encoded = Base64.encodeToString(
        libraryName.toByteArray(),
        Base64.URL_SAFE or Base64.NO_WRAP,
      )
      return "$PATH?$KEY_LIBRARY_NAME=$encoded"
    }

    /**
     * Extracts the library name from the [Bundle] arguments.
     */
    fun getLibraryName(args: Bundle): String {
      val libraryName = Base64.decode(
        requireNotNull(args.getString(KEY_LIBRARY_NAME)),
        Base64.URL_SAFE or Base64.NO_WRAP,
      )
      return String(libraryName)
    }
  }
}
