package com.github.droibit.oss_licenses.ui.wear

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.github.droibit.oss_licenses.ui.viewmodel.OssLicenseViewModel
import com.github.droibit.oss_licenses.ui.wear.internal.OssLicenseListFragment

/**
 * An activity that displays open source licenses.
 */
@Deprecated(message = "Please migrate to the Wear Compose version of WearableOssLicensesActivity.")
class WearableOssLicensesActivity : FragmentActivity(R.layout.activity_wearable_oss_licenses) {
  private val viewModel: OssLicenseViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    if (savedInstanceState == null) {
      viewModel.loadLicenses(this)

      supportFragmentManager
        .beginTransaction()
        .replace(
          R.id.oss_licenses_content,
          OssLicenseListFragment.newInstance(),
        ).commit()
    }
  }

  companion object {
    /**
     * Creates an [Intent] to start the [WearableOssLicensesActivity].
     *
     * @return An [Intent] that can be used to start the [WearableOssLicensesActivity].
     */
    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated(
      message = "Please migrate to the Wear Compose version of WearableOssLicensesActivity.",
    )
    @JvmStatic
    fun createIntent(context: Context): Intent =
      Intent(context, WearableOssLicensesActivity::class.java)
  }
}
