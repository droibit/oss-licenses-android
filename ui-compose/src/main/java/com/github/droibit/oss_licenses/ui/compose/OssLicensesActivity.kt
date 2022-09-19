package com.github.droibit.oss_licenses.ui.compose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import com.github.droibit.oss_licenses.ui.compose.internal.OssLicenseGraph

class OssLicensesActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
      MaterialTheme(colorScheme = colorScheme) {
        OssLicenseGraph()
      }
    }
  }

  companion object {

    internal const val EXTRA_IGNORE_LIBRARIES =
      "com.github.droibit.oss_licenses.ui.compose.EXTRA_IGNORE_LIBRARIES"

    @JvmStatic
    @JvmOverloads
    fun createIntent(
      context: Context,
      ignoreLibraries: Set<String> = emptySet(),
    ): Intent = Intent(context, OssLicensesActivity::class.java)
      .putStringArrayListExtra(EXTRA_IGNORE_LIBRARIES, ArrayList(ignoreLibraries))
  }
}
