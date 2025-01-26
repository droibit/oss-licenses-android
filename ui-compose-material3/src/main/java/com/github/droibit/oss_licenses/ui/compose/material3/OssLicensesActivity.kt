package com.github.droibit.oss_licenses.ui.compose.material3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.github.droibit.oss_licenses.ui.compose.material3.internal.OssLicenseNavGraph
import com.github.droibit.oss_licenses.ui.compose.material3.internal.OssLicensesTheme

/**
 * An activity that displays open source licenses.
 */
class OssLicensesActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge()
    super.onCreate(savedInstanceState)

    setContent {
      OssLicensesTheme {
        OssLicenseNavGraph()
      }
    }
  }

  companion object {
    /**
     * Creates an [Intent] to start the [OssLicensesActivity].
     *
     * @return An [Intent] that can be used to start the [OssLicensesActivity].
     */
    @JvmStatic
    fun createIntent(context: Context): Intent = Intent(context, OssLicensesActivity::class.java)
  }
}
