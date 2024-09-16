package com.github.droibit.oss_licenses.ui.wear.compose.core

import androidx.annotation.RestrictTo
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
import androidx.wear.compose.foundation.rotary.RotaryScrollableDefaults
import androidx.wear.compose.foundation.rotary.rotaryScrollable
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.compose.OssLicenseCollection

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
@OptIn(ExperimentalWearFoundationApi::class)
@Composable
fun OssLicenseList(
  licenses: OssLicenseCollection,
  header: @Composable ScalingLazyListItemScope.() -> Unit,
  listItem: @Composable ScalingLazyListItemScope.(OssLicense) -> Unit,
  modifier: Modifier = Modifier,
  listState: ScalingLazyListState = rememberScalingLazyListState(),
  focusRequester: FocusRequester = rememberActiveFocusRequester(),
) {
  ScalingLazyColumn(
    modifier = modifier
      .rotaryScrollable(
        RotaryScrollableDefaults.behavior(
          listState,
          hapticFeedbackEnabled = false,
        ),
        focusRequester,
      ),
    state = listState,
  ) {
    if (licenses().isNotEmpty()) {
      item {
        header()
      }
    }
    items(
      licenses(),
      key = OssLicense::libraryName,
      itemContent = listItem,
    )
  }
}
