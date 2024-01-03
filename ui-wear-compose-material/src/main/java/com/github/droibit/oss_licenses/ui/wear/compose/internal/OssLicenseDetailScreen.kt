package com.github.droibit.oss_licenses.ui.wear.compose.internal

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.ExperimentalWearFoundationApi
import androidx.wear.compose.foundation.rememberActiveFocusRequester
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import com.github.droibit.oss_licenses.parser.OssLicense
import kotlin.math.sqrt

@OptIn(ExperimentalWearFoundationApi::class)
@Composable
fun OssLicenseDetailScreen(
  license: OssLicense,
  modifier: Modifier = Modifier,
  listState: LazyListState = rememberLazyListState(),
  focusRequester: FocusRequester = rememberActiveFocusRequester(),
) {
  Scaffold(
    modifier = modifier,
    positionIndicator = {
      PositionIndicator(lazyListState = listState)
    },
  ) {
    val configuration = LocalConfiguration.current
    val inset = remember {
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
      modifier = Modifier
        .fillMaxSize()
        .rotaryScrollable(focusRequester, listState),
      horizontalAlignment = Alignment.CenterHorizontally,
      state = listState,
      contentPadding = PaddingValues(inset),
    ) {
      item {
        ListHeader {
          Text(
            text = license.libraryName,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
          )
        }
      }

      item {
        Text(
          text = license.license,
          modifier = Modifier
            .fillMaxWidth(),
          style = MaterialTheme.typography.caption1.copy(
            fontFamily = FontFamily.Monospace,
          ),
        )
      }
    }
  }
}
