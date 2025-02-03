package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.foundation.lazy.TransformingLazyColumnState
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.ScreenScaffold
import com.github.droibit.oss_licenses.parser.OssLicense

@Composable
fun OssLicenseDetailScreen(
  license: OssLicense,
  modifier: Modifier = Modifier,
  listState: TransformingLazyColumnState = rememberTransformingLazyColumnState(),
) {
  ScreenScaffold(
    scrollState = listState,
    modifier = modifier,
  ) {
    OssLicenseDetail(
      license = license,
      listState = listState,
      modifier = modifier,
    )
  }
}
