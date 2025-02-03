package com.github.droibit.oss_licenses.ui.wear.compose.material.internal.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.Text
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.wear.compose.material.R

@Composable
internal fun OssLicenseList(
  licenses: List<OssLicense>,
  modifier: Modifier = Modifier,
  onItemClick: (OssLicense) -> Unit = {},
  listState: ScalingLazyListState = rememberScalingLazyListState(),
) {
  ScalingLazyColumn(
    modifier = modifier,
    state = listState,
  ) {
    item {
      ListHeader {
        Text(
          text = stringResource(id = R.string.oss_licenses_title),
          textAlign = TextAlign.Center,
        )
      }
    }
    items(
      licenses,
      key = OssLicense::library,
    ) { license ->
      OssLicenseItem(
        license = license,
        onClick = onItemClick,
      )
    }
  }
}
