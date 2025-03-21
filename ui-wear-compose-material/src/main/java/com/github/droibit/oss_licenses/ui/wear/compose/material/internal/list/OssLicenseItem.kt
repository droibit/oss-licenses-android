package com.github.droibit.oss_licenses.ui.wear.compose.material.internal.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Text
import com.github.droibit.oss_licenses.ui.OssLicenseUiState

@Composable
internal fun OssLicenseItem(
  license: OssLicenseUiState,
  modifier: Modifier = Modifier,
  onClick: (OssLicenseUiState) -> Unit = {},
) {
  Chip(
    onClick = {
      onClick(license)
    },
    label = {
      Text(text = license.library)
    },
    modifier = modifier.fillMaxWidth(),
    colors = ChipDefaults.secondaryChipColors(),
  )
}
