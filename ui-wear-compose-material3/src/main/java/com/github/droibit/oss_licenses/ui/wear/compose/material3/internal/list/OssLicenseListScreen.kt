package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.compose.foundation.lazy.TransformingLazyColumnState
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.ListHeader
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.Text
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.wear.compose.material3.R

@Composable
internal fun OssLicenseListScreen(
  licenses: List<OssLicense>,
  modifier: Modifier = Modifier,
  listState: TransformingLazyColumnState = rememberTransformingLazyColumnState(),
  onNavigateToDetail: (OssLicense) -> Unit = {},
) {
  ScreenScaffold(
    scrollState = listState,
    modifier = modifier,
  ) {
    OssLicenseList(
      licenses = licenses,
      header = {
        ListHeader {
          Text(
            text = stringResource(id = R.string.oss_licenses_title),
            textAlign = TextAlign.Center,
          )
        }
      },
      listItem = { license ->
        OssLicenseItem(
          license = license,
          onClick = onNavigateToDetail,
        )
      },
      listState = listState,
    )
  }
}

