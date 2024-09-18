package com.github.droibit.oss_licenses.ui.compose.material3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import com.github.droibit.oss_licenses.ui.compose.material3.internal.OssLicenseNavGraph
import com.github.droibit.oss_licenses.ui.viewmodel.OssLicenseViewModel.Companion.EXTRA_IGNORE_LIBRARIES

/**
 * An activity that displays open source licenses.
 */
class OssLicensesActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge()
    super.onCreate(savedInstanceState)

    setContent {
      val colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
      MaterialTheme(colorScheme = colorScheme) {
        OssLicenseNavGraph()
      }
    }
  }

  companion object {
    /**
     * Creates an [Intent] to start the [OssLicensesActivity].
     *
     * @param ignoreLibraries A set of library names to be ignored when displaying the licenses. Default is an empty set.
     * @return An [Intent] that can be used to start the [OssLicensesActivity].
     */
    @JvmStatic
    @JvmOverloads
    fun createIntent(
      context: Context,
      ignoreLibraries: Set<String> = emptySet(),
    ): Intent = Intent(context, OssLicensesActivity::class.java)
      .putStringArrayListExtra(EXTRA_IGNORE_LIBRARIES, ArrayList(ignoreLibraries))
  }
}
