package com.github.droibit.oss_licenses.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.github.droibit.oss_licenses.sample.ui.Theme
import com.github.droibit.oss_licenses.ui.compose.material3.OssLicensesActivity

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge()
    super.onCreate(savedInstanceState)

    setContent {
      Theme {
        Scaffold(
          topBar = {
            CenterAlignedTopAppBar(
              title = {
                Text(text = stringResource(id = R.string.app_name))
              },
            )
          },
        ) { innerPadding ->
          val context = LocalContext.current
          Box(
            modifier = Modifier
              .padding(innerPadding)
              .fillMaxSize(),
          ) {
            Button(
              onClick = {
                context.startActivity(
                  OssLicensesActivity.createIntent(context),
                )
              },
              modifier = Modifier.align(Alignment.Center),
            ) {
              Text(text = "Show")
            }
          }
        }
      }
    }
  }
}
