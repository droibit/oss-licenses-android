package com.github.droibit.oss_licenses.ui.wearable

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity

class WearableOssLicensesActivity : FragmentActivity(R.layout.activity_wearable_oss_licenses) {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    if (savedInstanceState == null) {
      val ignoreLibraries =
        requireNotNull(intent.getStringArrayListExtra(EXTRA_IGNORE_LIBRARIES))
      supportFragmentManager.beginTransaction()
        .replace(
          R.id.oss_licenses_content,
          OssLicenseListFragment.newInstance(ignoreLibraries),
        )
        .commit()
    }
  }

  companion object {

    private const val EXTRA_IGNORE_LIBRARIES =
      "com.github.droibit.oss_licenses.ui.wearable.EXTRA_IGNORE_LIBRARIES"

    @JvmStatic
    @JvmOverloads
    fun createIntent(
      context: Context,
      ignoreLibraries: Set<String> = emptySet(),
    ): Intent = Intent(context, WearableOssLicensesActivity::class.java)
      .putStringArrayListExtra(EXTRA_IGNORE_LIBRARIES, ArrayList(ignoreLibraries))
  }
}
