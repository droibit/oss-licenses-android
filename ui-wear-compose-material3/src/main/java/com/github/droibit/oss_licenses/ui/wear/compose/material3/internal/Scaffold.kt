package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// TODO: Delete when Scaffold is introduced to `wear-compose-material3`.
@Composable
internal fun Scaffold(
  modifier: Modifier = Modifier,
  vignette: @Composable (() -> Unit)? = null,
  positionIndicator: @Composable (() -> Unit)? = null,
  pageIndicator: @Composable (() -> Unit)? = null,
  timeText: @Composable (() -> Unit)? = null,
  content: @Composable () -> Unit,
) {
  Box(modifier = modifier) {
    content()
    vignette?.invoke()
    positionIndicator?.invoke()
    pageIndicator?.invoke()
    timeText?.invoke()
  }
}
