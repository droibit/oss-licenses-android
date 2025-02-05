package com.github.droibit.oss_licenses.ui.wear.compose.material.internal.detail

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import com.github.droibit.oss_licenses.parser.OssLicense

@Composable
internal fun OssLicenseDetailScreen(
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
      listState = listState,
    )
  }
}
