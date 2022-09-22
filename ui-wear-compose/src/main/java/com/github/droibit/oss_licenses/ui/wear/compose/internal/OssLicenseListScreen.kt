package com.github.droibit.oss_licenses.ui.wear.compose.internal

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.ScalingLazyListState
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.items
import androidx.wear.compose.material.rememberScalingLazyListState
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.viewmodel.OssLicenseViewModel
import com.github.droibit.oss_licenses.ui.wear.compose.R
import com.github.droibit.oss_licenses.ui.wear.compose.internal.OssLicenseNavGraph.Directions.toDetail
import com.github.droibit.oss_licenses.ui.wear.compose.internal.OssLicenseNavGraph.ROUTE_LIST

@Composable
internal fun OssLicenseListScreen(
  navController: NavController,
  modifier: Modifier = Modifier,
  viewModel: OssLicenseViewModel,
) {
  Scaffold(modifier = modifier) {
    val licenses by viewModel.licenses.collectAsState()
    OssLicenseList(
      licenses = licenses,
      modifier = Modifier
        .fillMaxSize(),
      onItemClick = { license ->
        with(navController) {
          if (currentDestination?.route == ROUTE_LIST) {
            navigate(toDetail(license.libraryName))
          }
        }
      },
    )
  }
}

@Composable
internal fun OssLicenseList(
  licenses: List<OssLicense>,
  modifier: Modifier = Modifier,
  listState: ScalingLazyListState = rememberScalingLazyListState(),
  onItemClick: (OssLicense) -> Unit = {},
) {
  val focusRequester = remember { FocusRequester() }
  LaunchedEffect(Unit) {
    focusRequester.requestFocus()
  }
  ScalingLazyColumn(
    modifier = modifier
      .rotaryScrollable(focusRequester, listState),
    state = listState,
  ) {
    if (licenses.isNotEmpty()) {
      item {
        ListHeader() {
          Text(text = stringResource(id = R.string.oss_licenses_title))
        }
      }
    }
    items(
      licenses,
      key = { it.libraryName },
    ) { license ->
      OssLicenseItem(
        license = license,
        onClick = {
          onItemClick(license)
        },
      )
    }
  }
}

@Composable
internal fun OssLicenseItem(
  modifier: Modifier = Modifier,
  license: OssLicense,
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
