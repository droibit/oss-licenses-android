package com.github.droibit.oss_licenses.sample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import com.github.droibit.oss_licenses.sample.theme.WearAppTheme
import com.github.droibit.oss_licenses.ui.wear.WearableOssLicensesActivity
import com.github.droibit.oss_licenses.ui.wear.compose.WearableOssLicensesActivity as WearableComposeOssLicensesActivity

private val ignoreLibraries = setOf(
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
)

class MainActivity : FragmentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      WearAppTheme {
        Scaffold(
          timeText = {
            TimeText()
          },
        ) {
          ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
          ) {
            item {
              Spacer(modifier = Modifier.fillMaxSize())
            }
            item {
              Chip(
                label = {
                  Text(text = "Show(Android View)")
                },
                colors = ChipDefaults.secondaryChipColors(),
                onClick = {
                  val intent = WearableOssLicensesActivity.createIntent(
                    this@MainActivity,
                    ignoreLibraries,
                  )
                  startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth(),
              )
            }
            item {
              Chip(
                label = {
                  Text(text = "Show(Compose)")
                },
                colors = ChipDefaults.secondaryChipColors(),
                onClick = {
                  val intent = WearableComposeOssLicensesActivity.createIntent(
                    this@MainActivity,
                    ignoreLibraries,
                  )
                  startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth(),
              )
            }
          }
        }
      }
    }
  }
}
