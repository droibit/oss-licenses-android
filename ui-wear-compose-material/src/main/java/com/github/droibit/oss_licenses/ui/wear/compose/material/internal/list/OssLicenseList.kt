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
import com.github.droibit.oss_licenses.ui.OssLicenseUiState
import com.github.droibit.oss_licenses.ui.wear.compose.material.R

@Composable
internal fun OssLicenseList(
  licenses: List<OssLicenseUiState>,
  modifier: Modifier = Modifier,
  onItemClick: (OssLicenseUiState) -> Unit = {},
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
      key = OssLicenseUiState::id,
    ) { license ->
      OssLicenseItem(
        license = license,
        onClick = onItemClick,
      )
    }
  }
}
