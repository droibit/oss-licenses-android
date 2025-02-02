package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal

import androidx.annotation.RestrictTo
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.ExperimentalWearFoundationApi
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.TransformingLazyColumnItemScope
import androidx.wear.compose.foundation.lazy.TransformingLazyColumnState
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import com.github.droibit.oss_licenses.parser.OssLicense
import kotlin.math.ceil

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
@OptIn(ExperimentalWearFoundationApi::class)
@Composable
fun OssLicenseList(
  licenses: List<OssLicense>,
  header: @Composable TransformingLazyColumnItemScope.() -> Unit,
  listItem: @Composable TransformingLazyColumnItemScope.(OssLicense) -> Unit,
  modifier: Modifier = Modifier,
  listState: TransformingLazyColumnState = rememberTransformingLazyColumnState(),
) {
  TransformingLazyColumn(
    modifier = modifier,
    state = listState,
    contentPadding = rememberResponsiveColumnPadding()
  ) {
    if (licenses.isNotEmpty()) {
      item {
        header()
      }
    }
    items(
      licenses,
      key = OssLicense::library,
      itemContent = listItem,
    )
  }
}

@Composable
fun rememberResponsiveColumnPadding(): PaddingValues {
  val configuration = LocalConfiguration.current
  val screenHeightDp = configuration.screenHeightDp.dp
  val screenWidthDp = configuration.screenWidthDp.dp

  val horizontalPadding = (screenWidthDp * 0.052f).ceilPx()

  return PaddingValues(
    top = (screenHeightDp * 0.1664f).ceilPx(),
    bottom = (screenHeightDp * 0.3646f).ceilPx(),
    start = horizontalPadding,
    end = horizontalPadding,
  )
}

@Composable
internal fun Dp.ceilPx(): Dp {
  val density = LocalDensity.current

  return with(density) {
    ceil(this@ceilPx.toPx()).toDp()
  }
}
