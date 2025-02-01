package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.wear.compose.material3.ListHeader
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.Text
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.wear.compose.core.OssLicenseDetail

@Composable
fun OssLicenseDetailScreen(
  license: OssLicense,
  modifier: Modifier = Modifier,
  listState: LazyListState = rememberLazyListState(),
) {
  ScreenScaffold(
    scrollState = listState,
    modifier = modifier,
  ) {
    OssLicenseDetail(
      license = license,
      header = {
        ListHeader {
          Text(
            text = license.library,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
          )
        }
      },
      content = { license ->
        Text(
          text = license.text,
          modifier = Modifier.fillMaxWidth(),
          style = MaterialTheme.typography.bodyMedium.copy(
            fontFamily = FontFamily.Monospace,
          ),
        )
      },
      listState = listState,
    )
  }
}
