package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal

import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import kotlinx.coroutines.launch

internal fun Modifier.rotaryScrollable(
  focusRequester: FocusRequester,
  scrollableState: ScrollableState,
): Modifier = composed {
  val coroutineScope = rememberCoroutineScope()
  onRotaryScrollEvent { event ->
    coroutineScope.launch {
      scrollableState.scrollBy(event.verticalScrollPixels)
      scrollableState.animateScrollBy(0f)
    }
    true
  }
    .focusRequester(focusRequester)
    .focusable()
}
