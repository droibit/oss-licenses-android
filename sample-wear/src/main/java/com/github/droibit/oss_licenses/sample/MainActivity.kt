package com.github.droibit.oss_licenses.sample

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.github.droibit.oss_licenses.ui.wearable.WearableOssLicensesActivity
import kotlinx.android.synthetic.main.activity_main.show

class MainActivity : FragmentActivity(R.layout.activity_main) {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    show.setOnClickListener {
      val intent = WearableOssLicensesActivity.createIntent(
          this,
          listOf("com.google.errorprone:javac-shaded-9-dev-r4023-3")
      )
      startActivity(intent)
    }
  }
}
