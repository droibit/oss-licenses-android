package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.TransformingLazyColumnItemScope
import androidx.wear.compose.foundation.lazy.TransformingLazyColumnState
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.wear.compose.material3.internal.rememberResponsiveColumnPadding

@Composable
internal fun OssLicenseList(
  licenses: List<OssLicense>,
  header: @Composable TransformingLazyColumnItemScope.() -> Unit,
  listItem: @Composable TransformingLazyColumnItemScope.(OssLicense) -> Unit,
  modifier: Modifier = Modifier,
  listState: TransformingLazyColumnState = rememberTransformingLazyColumnState(),
) {
  TransformingLazyColumn(
    modifier = modifier,
    state = listState,
    contentPadding = rememberResponsiveColumnPadding(),
  ) {
    item {
      header()
    }

    items(
      licenses,
      key = OssLicense::library,
      itemContent = listItem,
    )
  }
}
