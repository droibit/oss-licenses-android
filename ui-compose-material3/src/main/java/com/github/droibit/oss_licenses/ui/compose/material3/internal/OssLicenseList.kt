package com.github.droibit.oss_licenses.ui.compose.material3.internal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.droibit.oss_licenses.ui.OssLicenseUiState

@Composable
internal fun OssLicenseList(
  licenses: List<OssLicenseUiState>,
  modifier: Modifier = Modifier,
  listState: LazyListState = rememberLazyListState(),
  onItemClick: (OssLicenseUiState) -> Unit = {},
) {
  LazyColumn(
    state = listState,
    modifier = modifier,
  ) {
    items(
      licenses,
      key = OssLicenseUiState::id,
    ) { license ->
      OssLicenseItem(
        license = license,
        onClick = onItemClick,
      )
      HorizontalDivider(
        modifier = Modifier
          .fillMaxSize()
          .padding(horizontal = 16.dp),
      )
    }
  }
}

@Composable
private fun OssLicenseItem(
  license: OssLicenseUiState,
  modifier: Modifier = Modifier,
  onClick: (OssLicenseUiState) -> Unit = {},
) {
  ListItem(
    headlineContent = {
      Text(text = license.library)
    },
    modifier = modifier.clickable(
      onClick = {
        onClick(license)
      },
    ),
  )
}
