package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal.detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.TransformingLazyColumnState
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.ListHeader
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.Text
import com.github.droibit.oss_licenses.ui.OssLicenseUiState
import com.github.droibit.oss_licenses.ui.wear.compose.material3.internal.rememberResponsiveColumnPadding

@Composable
internal fun OssLicenseDetail(
  license: OssLicenseUiState,
  modifier: Modifier = Modifier,
  listState: TransformingLazyColumnState = rememberTransformingLazyColumnState(),
) {
  TransformingLazyColumn(
    modifier = modifier,
    state = listState,
    contentPadding = rememberResponsiveColumnPadding(),
  ) {
    item {
      ListHeader {
        Text(
          text = license.library,
          textAlign = TextAlign.Center,
          overflow = TextOverflow.Ellipsis,
        )
      }
    }

    item {
      Text(
        text = license.licenseText,
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.bodyMedium.copy(
          fontFamily = FontFamily.Monospace,
        ),
      )
    }
  }
}
