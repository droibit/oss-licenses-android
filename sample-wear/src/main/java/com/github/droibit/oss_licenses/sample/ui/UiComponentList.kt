package com.github.droibit.oss_licenses.sample.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.PositionIndicator
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnDefaults
import com.google.android.horologist.compose.layout.ScalingLazyColumnState
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.google.android.horologist.compose.layout.rememberResponsiveColumnState
import com.google.android.horologist.compose.material.Chip
import com.google.android.horologist.compose.material.ListHeaderDefaults
import com.google.android.horologist.compose.material.ResponsiveListHeader
import com.google.android.horologist.compose.material.Title

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun UiComponentListScreen(
  onItemClick: (UiComponentType) -> Unit,
  modifier: Modifier = Modifier,
  columnState: ScalingLazyColumnState = rememberResponsiveColumnState(
    contentPadding = ScalingLazyColumnDefaults.padding(
      first = ScalingLazyColumnDefaults.ItemType.Text,
      last = ScalingLazyColumnDefaults.ItemType.Chip,
    ),
  ),
) {
  ScreenScaffold(
    scrollState = columnState,
    positionIndicator = {
      PositionIndicator(columnState.state)
    },
  ) {
    UiComponentList(
      columnState = columnState,
      onItemClick = onItemClick,
      modifier = modifier,
    )
  }
}

@OptIn(ExperimentalHorologistApi::class)
@Composable
private fun UiComponentList(
  columnState: ScalingLazyColumnState,
  onItemClick: (UiComponentType) -> Unit,
  modifier: Modifier = Modifier,
) {
  ScalingLazyColumn(
    columnState = columnState,
    modifier = modifier.fillMaxSize(),
  ) {
    item {
      ResponsiveListHeader(
        contentPadding = ListHeaderDefaults.firstItemPadding(),
      ) {
        Title(text = "Sample for Wear OS")
      }
    }

    items(UiComponentType.entries) { item ->
      ListItem(
        label = "Show (${item.description})",
        onClick = {
          onItemClick(item)
        },
      )
    }
  }
}

@OptIn(ExperimentalHorologistApi::class)
@Composable
private fun ListItem(
  label: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Chip(
    label = label,
    colors = ChipDefaults.secondaryChipColors(),
    onClick = onClick,
    modifier = modifier.fillMaxWidth(),
  )
}
