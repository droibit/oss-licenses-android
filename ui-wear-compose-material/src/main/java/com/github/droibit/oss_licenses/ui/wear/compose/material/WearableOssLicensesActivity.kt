package com.github.droibit.oss_licenses.ui.wear.compose.material

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.wear.compose.material.MaterialTheme
import com.github.droibit.oss_licenses.ui.wear.compose.material.internal.OssLicensesApp

/**
 * An activity that displays open source licenses.
 */
class WearableOssLicensesActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      MaterialTheme {
        OssLicensesApp()
      }
    }
  }

  companion object {
    /**
     * Creates an [Intent] to start the [WearableOssLicensesActivity].
     *
     * @return An [Intent] that can be used to start the [WearableOssLicensesActivity].
     */
    @JvmStatic
    fun createIntent(context: Context) = Intent(context, WearableOssLicensesActivity::class.java)
  }
}
