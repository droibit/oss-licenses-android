package com.github.droibit.oss_licenses.sample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Text
import com.github.droibit.oss_licenses.sample.theme.WearAppTheme
import com.github.droibit.oss_licenses.ui.wear.WearableOssLicensesActivity
import com.github.droibit.oss_licenses.ui.wear.compose.material.WearableOssLicensesActivity as WearableMaterialOssLicensesActivity
import com.github.droibit.oss_licenses.ui.wear.compose.material3.WearableOssLicensesActivity as WearableMaterial3OssLicensesActivity
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.layout.AppScaffold
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.rememberColumnState

private val IgnoreLibraries = setOf(
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

@OptIn(ExperimentalHorologistApi::class)
class MainActivity : FragmentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      WearAppTheme {
        AppScaffold {
          ScalingLazyColumn(
            columnState = rememberColumnState(),
            modifier = Modifier.fillMaxSize(),
          ) {
            item {
              Spacer(modifier = Modifier.fillMaxSize())
            }
            item {
              ListItem(
                label = "Show (Android View)",
                onClick = {
                  val intent = WearableOssLicensesActivity.createIntent(
                    this@MainActivity,
                    IgnoreLibraries,
                  )
                  startActivity(intent)
                },
              )
            }
            item {
              ListItem(
                label = "Show (Compose M2)",
                onClick = {
                  val intent = WearableMaterialOssLicensesActivity.createIntent(
                    this@MainActivity,
                    IgnoreLibraries,
                  )
                  startActivity(intent)
                },
              )
            }
            item {
              ListItem(
                label = "Show (Compose M3)",
                onClick = {
                  val intent = WearableMaterial3OssLicensesActivity.createIntent(
                    this@MainActivity,
                    IgnoreLibraries,
                  )
                  startActivity(intent)
                },
              )
            }
          }
        }
      }
    }
  }
}

@Composable
private fun ListItem(
  label: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Chip(
    label = {
      Text(text = label)
    },
    colors = ChipDefaults.secondaryChipColors(),
    onClick = onClick,
    modifier = modifier.fillMaxWidth(),
  )
}
