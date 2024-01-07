package com.github.droibit.oss_licenses.ui.wear.compose.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.wear.compose.foundation.ExperimentalWearFoundationApi
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListItemScope
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.foundation.rememberActiveFocusRequester
import com.github.droibit.oss_licenses.parser.OssLicense

@OptIn(ExperimentalWearFoundationApi::class)
@Composable
fun OssLicenseList(
  licenses: List<OssLicense>,
  header: @Composable ScalingLazyListItemScope.() -> Unit,
  listItem: @Composable ScalingLazyListItemScope.(OssLicense) -> Unit,
  modifier: Modifier = Modifier,
  listState: ScalingLazyListState = rememberScalingLazyListState(),
  focusRequester: FocusRequester = rememberActiveFocusRequester(),
) {
  ScalingLazyColumn(
    modifier = modifier
      .rotaryScrollable(focusRequester, listState),
    state = listState,
  ) {
    if (licenses.isNotEmpty()) {
      item {
        header()
      }
    }
    items(
      licenses,
      key = { it.libraryName },
    ) { license ->
      listItem(license)
    }
  }
}
