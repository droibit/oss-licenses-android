package com.github.droibit.oss_licenses.ui.wear.compose.material.internal.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import com.github.droibit.oss_licenses.ui.OssLicenseUiState

@Composable
internal fun OssLicenseListScreen(
  licenses: List<OssLicenseUiState>,
  modifier: Modifier = Modifier,
  listState: ScalingLazyListState = rememberScalingLazyListState(),
  onNavigateToDetail: (OssLicenseUiState) -> Unit = {},
) {
  Scaffold(
    modifier = modifier,
    positionIndicator = {
      PositionIndicator(scalingLazyListState = listState)
    },
  ) {
    OssLicenseList(
      licenses = licenses,
      modifier = Modifier.fillMaxSize(),
      listState = listState,
      onItemClick = onNavigateToDetail,
    )
  }
}
