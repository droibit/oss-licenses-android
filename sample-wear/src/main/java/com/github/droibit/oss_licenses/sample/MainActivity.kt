package com.github.droibit.oss_licenses.sample

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.github.droibit.oss_licenses.sample.databinding.ActivityMainBinding
import com.github.droibit.oss_licenses.ui.wear.WearableOssLicensesActivity

class MainActivity : FragmentActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.show.setOnClickListener {
      val intent = WearableOssLicensesActivity.createIntent(
        this,
        setOf(
          "kotlinx-coroutines-bom",
          "Android Tracing",
          "Animal Sniffer",
          "Checker Framework Annotations",
          "Error Prone",
          "Guava JDK5",
          "Guava JDK7",
          "J2ObjC",
          "JSR 305",
          "JsInterop Annotations",
          "apksig",
          "JSpecify",
        ),
      )
      startActivity(intent)
    }
  }
}
