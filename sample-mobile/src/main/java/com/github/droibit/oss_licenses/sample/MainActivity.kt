package com.github.droibit.oss_licenses.sample

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import com.github.droibit.oss_licenses.sample.ui.MobileAppTheme
import com.github.droibit.oss_licenses.ui.compose.material3.OssLicensesActivity as ComposeM3OssLicensesActivity
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge()
    super.onCreate(savedInstanceState)

    setContent {
      MobileAppTheme {
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
            Column(
              verticalArrangement = Arrangement.spacedBy(8.dp),
              horizontalAlignment = Alignment.CenterHorizontally,
              modifier = Modifier.align(Alignment.Center),
            ) {
              Button(
                onClick = {
                  context.startActivity(
                    ComposeM3OssLicensesActivity.createIntent(context),
                  )
                },
              ) {
                Text(text = "Show")
              }

              Button(
                onClick = {
                  context.startActivity(
                    Intent(context, OssLicensesMenuActivity::class.java),
                  )
                },
              ) {
                Text(text = "Show (Play Services)")
              }
            }
          }
        }
      }
    }
  }
}
