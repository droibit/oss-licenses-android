package com.github.droibit.oss_licenses.ui.wear.compose.material

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.wear.compose.material.MaterialTheme
import com.github.droibit.oss_licenses.ui.viewmodel.OssLicenseViewModel.Companion.EXTRA_IGNORE_LIBRARIES
import com.github.droibit.oss_licenses.ui.wear.compose.material.internal.OssLicenseNavGraph

/**
 * An activity that displays open source licenses.
 */
class WearableOssLicensesActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      MaterialTheme {
        OssLicenseNavGraph()
      }
    }
  }

  companion object {
    /**
     * Creates an [Intent] to start the [WearableOssLicensesActivity].
     *
     * @param ignoreLibraries A set of library names to be ignored when displaying the licenses. Default is an empty set.
     * @return An [Intent] that can be used to start the [WearableOssLicensesActivity].
     */
    @JvmStatic
    @JvmOverloads
    fun createIntent(
      context: Context,
      ignoreLibraries: Set<String> = emptySet(),
    ): Intent = Intent(context, WearableOssLicensesActivity::class.java)
      .putStringArrayListExtra(EXTRA_IGNORE_LIBRARIES, ArrayList(ignoreLibraries))
  }
}
