package com.github.droibit.oss_licenses.ui.compose.material3.internal

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.github.droibit.oss_licenses.parser.OssLicense

@Composable
internal fun OssLicenseDetailScreen(
  license: OssLicense,
  modifier: Modifier = Modifier,
  scrollState: ScrollState = rememberScrollState(),
  onNavigateBack: () -> Unit = {},
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
          BackNavigationButton(onClick = onNavigateBack)
        },
      )
    },
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .padding(innerPadding)
        .fillMaxSize()
        .verticalScroll(scrollState),
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
