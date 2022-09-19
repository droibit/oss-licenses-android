package com.github.droibit.oss_licenses.ui.wear

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.github.droibit.oss_licenses.ui.viewmodel.OssLicenseViewModel
import com.github.droibit.oss_licenses.ui.viewmodel.OssLicenseViewModel.Companion.EXTRA_IGNORE_LIBRARIES
import com.github.droibit.oss_licenses.ui.wear.internal.OssLicenseListFragment

class WearableOssLicensesActivity : FragmentActivity(R.layout.activity_wearable_oss_licenses) {
  private val viewModel: OssLicenseViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    if (savedInstanceState == null) {
      viewModel.ensureLicenses()

      supportFragmentManager.beginTransaction()
        .replace(
          R.id.oss_licenses_content,
          OssLicenseListFragment.newInstance(),
        )
        .commit()
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
