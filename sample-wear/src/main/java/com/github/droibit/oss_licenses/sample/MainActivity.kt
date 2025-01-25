package com.github.droibit.oss_licenses.sample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import com.github.droibit.oss_licenses.sample.ui.UiComponentListScreen
import com.github.droibit.oss_licenses.sample.ui.UiComponentType
import com.github.droibit.oss_licenses.sample.ui.WearAppTheme
import com.github.droibit.oss_licenses.ui.wear.WearableOssLicensesActivity
import com.github.droibit.oss_licenses.ui.wear.compose.material.WearableOssLicensesActivity as WearableM2OssLicensesActivity
import com.github.droibit.oss_licenses.ui.wear.compose.material3.WearableOssLicensesActivity as WearableM3OssLicensesActivity
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.layout.AppScaffold

@OptIn(ExperimentalHorologistApi::class)
class MainActivity : FragmentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      WearAppTheme {
        AppScaffold {
          UiComponentListScreen(
            onItemClick = this@MainActivity::onItemClick,
          )
        }
      }
    }
  }

  private fun onItemClick(uiType: UiComponentType) {
    val intent = when (uiType) {
      UiComponentType.ANDROID_VIEW -> WearableOssLicensesActivity.createIntent(this)
      UiComponentType.COMPOSE_M2 -> WearableM2OssLicensesActivity.createIntent(this)
      UiComponentType.COMPOSE_M3 -> WearableM3OssLicensesActivity.createIntent(this)
    }
    startActivity(intent)
  }
}
