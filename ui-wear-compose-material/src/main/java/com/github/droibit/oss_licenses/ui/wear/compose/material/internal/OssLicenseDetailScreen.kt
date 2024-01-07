package com.github.droibit.oss_licenses.ui.wear.compose.material.internal

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.wear.compose.core.OssLicenseDetail

@Composable
fun OssLicenseDetailScreen(
  license: OssLicense,
  modifier: Modifier = Modifier,
  listState: LazyListState = rememberLazyListState(),
) {
  Scaffold(
    modifier = modifier,
    positionIndicator = {
      PositionIndicator(lazyListState = listState)
    },
  ) {
    OssLicenseDetail(
      license = license,
      header = {
        ListHeader {
          Text(
            text = license.libraryName,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
          )
        }
      },
      content = { license ->
        Text(
          text = license.license,
          modifier = Modifier.fillMaxWidth(),
          style = MaterialTheme.typography.caption1.copy(
            fontFamily = FontFamily.Monospace,
          ),
        )
      },
      modifier = modifier,
      listState = listState,
    )
  }
}
