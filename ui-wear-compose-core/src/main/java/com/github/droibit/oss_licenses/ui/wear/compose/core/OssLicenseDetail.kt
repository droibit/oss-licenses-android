package com.github.droibit.oss_licenses.ui.wear.compose.core

import androidx.annotation.RestrictTo
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.ExperimentalWearFoundationApi
import androidx.wear.compose.foundation.rememberActiveFocusRequester
import com.github.droibit.oss_licenses.parser.OssLicense
import kotlin.math.sqrt

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
@OptIn(ExperimentalWearFoundationApi::class)
@Composable
fun OssLicenseDetail(
  license: OssLicense,
  modifier: Modifier = Modifier,
  listState: LazyListState = rememberLazyListState(),
  focusRequester: FocusRequester = rememberActiveFocusRequester(),
  header: @Composable LazyItemScope.() -> Unit = {},
  content: @Composable LazyItemScope.(OssLicense) -> Unit,
) {
  val configuration = LocalConfiguration.current
  val inset = remember(configuration) {
    if (configuration.isScreenRound) {
      val screenHeightDp = configuration.screenHeightDp
      val screenWidthDp = configuration.smallestScreenWidthDp
      val maxSquareEdge = (sqrt(((screenHeightDp * screenWidthDp) / 2).toDouble()))
      Dp(((screenHeightDp - maxSquareEdge) / 2).toFloat())
    } else {
      8.dp
    }
  }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .rotaryScrollable(focusRequester, listState),
    horizontalAlignment = Alignment.CenterHorizontally,
    state = listState,
    contentPadding = PaddingValues(inset),
  ) {
    item {
      header()
    }

    item {
      content(license)
    }
  }
}
