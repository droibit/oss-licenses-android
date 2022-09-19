package com.github.droibit.oss_licenses.ui.compose.internal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.droibit.oss_licenses.parser.OssLicense

@Composable
internal fun OssLicenseDetailScreen(
  navController: NavController,
  license: OssLicense,
  modifier: Modifier = Modifier,
) {
  Scaffold(
    modifier = modifier,
    topBar = {
      TopAppBar(
        title = {
          Text(
            text = license.libraryName,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
          )
        },
        navigationIcon = {
          IconButton(
            onClick = {
              navController.popBackStack()
            },
          ) {
            Icon(
              imageVector = Icons.Outlined.ArrowBack,
              contentDescription = null,
            )
          }
        },
      )
    },
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .padding(innerPadding)
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
    ) {
      Text(
        text = license.license,
        modifier = Modifier.padding(
          horizontal = 16.dp,
          vertical = 8.dp,
        ),
        style = MaterialTheme.typography.bodyMedium.copy(
          fontFamily = FontFamily.Monospace,
        ),
      )
    }
  }
}

@Preview
@Composable
private fun OssLicenseDetailScreenPreview() {
  MaterialTheme {
    OssLicenseDetailScreen(
      navController = rememberNavController(),
      license = OssLicense(
        libraryName = "OSS Licenses",
        license = "https://www.apache.org/licenses/LICENSE-2.0.txt",
      ),
    )
  }
}
