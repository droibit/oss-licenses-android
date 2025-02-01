package com.github.droibit.oss_licenses.ui.wear.compose.material.internal

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.wear.compose.core.OssLicenseList
import com.github.droibit.oss_licenses.ui.wear.compose.material.R

@Composable
internal fun OssLicenseListScreen(
  licenses: List<OssLicense>,
  modifier: Modifier = Modifier,
  listState: ScalingLazyListState = rememberScalingLazyListState(),
  onNavigateToDetail: (OssLicense) -> Unit = {},
) {
  Scaffold(
    modifier = modifier,
    positionIndicator = {
      PositionIndicator(scalingLazyListState = listState)
    },
  ) {
    OssLicenseListImpl(
      licenses = licenses,
      modifier = Modifier.fillMaxSize(),
      listState = listState,
      onItemClick = onNavigateToDetail,
    )
  }
}

@Composable
internal fun OssLicenseListImpl(
  licenses: List<OssLicense>,
  listState: ScalingLazyListState,
  onItemClick: (OssLicense) -> Unit,
  modifier: Modifier = Modifier,
) {
  OssLicenseList(
    licenses = licenses,
    header = {
      ListHeader {
        Text(
          text = stringResource(id = R.string.oss_licenses_title),
          textAlign = TextAlign.Center,
        )
      }
    },
    listItem = { license ->
      OssLicenseItem(
        license = license,
        onClick = onItemClick,
      )
    },
    modifier = modifier,
    listState = listState,
  )
}

@Composable
internal fun OssLicenseItem(
  license: OssLicense,
  modifier: Modifier = Modifier,
  onClick: (OssLicense) -> Unit = {},
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
