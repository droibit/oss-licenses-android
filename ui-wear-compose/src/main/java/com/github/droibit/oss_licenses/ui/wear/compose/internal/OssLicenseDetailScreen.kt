package com.github.droibit.oss_licenses.ui.wear.compose.internal

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import com.github.droibit.oss_licenses.parser.OssLicense

@Composable
fun OssLicenseDetailScreen(
  license: OssLicense,
  modifier: Modifier = Modifier,
) {
  val listState = rememberLazyListState()
  Scaffold(
    modifier = modifier,
    positionIndicator = {
      PositionIndicator(lazyListState = listState)
    },
  ) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
      focusRequester.requestFocus()
    }

    val rounded = LocalConfiguration.current.isScreenRound
    val contentPadding = if (rounded) PaddingValues(32.dp, 24.dp) else PaddingValues(4.dp, 4.dp)

    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .rotaryScrollable(focusRequester, listState),
      horizontalAlignment = Alignment.CenterHorizontally,
      state = listState,
      contentPadding = contentPadding,
    ) {
      item {
        ListHeader {
          Text(
            text = license.libraryName,
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
