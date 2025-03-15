package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.TransformingLazyColumnItemScope
import androidx.wear.compose.foundation.lazy.TransformingLazyColumnState
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import com.github.droibit.oss_licenses.ui.OssLicenseUiState
import com.github.droibit.oss_licenses.ui.wear.compose.material3.internal.rememberResponsiveColumnPadding

@Composable
internal fun OssLicenseList(
  licenses: List<OssLicenseUiState>,
  header: @Composable TransformingLazyColumnItemScope.() -> Unit,
  listItem: @Composable TransformingLazyColumnItemScope.(OssLicenseUiState) -> Unit,
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
      key = OssLicenseUiState::id,
      itemContent = listItem,
    )
  }
}
