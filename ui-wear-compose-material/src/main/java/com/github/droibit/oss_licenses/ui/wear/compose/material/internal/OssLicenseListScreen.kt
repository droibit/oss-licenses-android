package com.github.droibit.oss_licenses.ui.wear.compose.material.internal

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.compose.navigation.navigateToDetail
import com.github.droibit.oss_licenses.ui.wear.compose.core.OssLicenseCollection
import com.github.droibit.oss_licenses.ui.wear.compose.core.OssLicenseList
import com.github.droibit.oss_licenses.ui.wear.compose.material.R

@Composable
internal fun OssLicenseListScreen(
  licenses: State<List<OssLicense>>,
  navController: NavController,
  modifier: Modifier = Modifier,
  listState: ScalingLazyListState = rememberScalingLazyListState(),
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
      onItemClick = { license ->
        navController.navigateToDetail(license.libraryName)
      },
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
        onClick = {
          onItemClick(license)
        },
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
  onClick: () -> Unit = {},
) {
  Chip(
    onClick = onClick,
    label = {
      Text(text = license.libraryName)
    },
    modifier = modifier.fillMaxWidth(),
    colors = ChipDefaults.secondaryChipColors(),
  )
}
