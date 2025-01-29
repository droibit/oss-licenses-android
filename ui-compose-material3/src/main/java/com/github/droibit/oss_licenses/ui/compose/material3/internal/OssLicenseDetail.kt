package com.github.droibit.oss_licenses.ui.compose.material3.internal

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.github.droibit.oss_licenses.parser.OssLicense

@Composable
internal fun OssLicenseDetail(
  license: OssLicense,
  modifier: Modifier = Modifier,
  showLibraryName: Boolean = true,
  scrollState: ScrollState = rememberScrollState(),
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(scrollState),
  ) {
    if (showLibraryName) {
      Text(
        text = license.libraryName,
        style = MaterialTheme.typography.titleLarge.copy(
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        modifier = Modifier
          .padding(horizontal = 16.dp)
          .padding(top = 16.dp),
      )
    }

    Text(
      text = license.license,
      style = MaterialTheme.typography.bodyMedium.copy(
        fontFamily = FontFamily.Monospace,
      ),
      modifier = Modifier.padding(
        horizontal = 16.dp,
        vertical = 16.dp,
      ),
    )
  }
}
