package com.github.droibit.oss_licenses.ui.wear.compose.internal

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberScalingLazyListState
import com.github.droibit.oss_licenses.parser.OssLicense

// FIXME: If the license is long, we have to scroll upward to see the license name.
@Composable
fun OssLicenseDetailScreen(
  license: OssLicense,
  modifier: Modifier = Modifier,
) {
  Scaffold(
    modifier = modifier,
  ) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
      focusRequester.requestFocus()
    }
    val listState = rememberScalingLazyListState()
    ScalingLazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .rotaryScrollable(focusRequester, listState),
      state = listState,
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
        val rounded = LocalConfiguration.current.isScreenRound
        Text(
          text = license.license,
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = if (rounded) 8.dp else 0.dp),
          style = MaterialTheme.typography.caption1.copy(
            fontFamily = FontFamily.Monospace,
          ),
        )
      }
    }
  }
}
