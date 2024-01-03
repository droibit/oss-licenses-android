package com.github.droibit.oss_licenses.ui.wear.compose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.wear.compose.material.MaterialTheme
import com.github.droibit.oss_licenses.ui.viewmodel.OssLicenseViewModel
import com.github.droibit.oss_licenses.ui.viewmodel.OssLicenseViewModel.Companion.EXTRA_IGNORE_LIBRARIES
import com.github.droibit.oss_licenses.ui.wear.compose.internal.OssLicenseNavGraph

class WearableOssLicensesActivity : ComponentActivity() {
  private val viewModel: OssLicenseViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (savedInstanceState == null) {
      viewModel.ensureLicenses()
    }
    setContent {
      MaterialTheme {
        OssLicenseNavGraph(
          viewModel = viewModel,
        )
      }
    }
  }

  companion object {

    @JvmStatic
    @JvmOverloads
    fun createIntent(
      context: Context,
      ignoreLibraries: Set<String> = emptySet(),
    ): Intent = Intent(context, WearableOssLicensesActivity::class.java)
      .putStringArrayListExtra(EXTRA_IGNORE_LIBRARIES, ArrayList(ignoreLibraries))
  }
}
