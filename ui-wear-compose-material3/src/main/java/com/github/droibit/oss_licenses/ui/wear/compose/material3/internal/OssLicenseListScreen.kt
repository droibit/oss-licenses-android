package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.wear.compose.foundation.ExperimentalWearFoundationApi
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.foundation.rememberActiveFocusRequester
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.ButtonDefaults
import androidx.wear.compose.material3.ListHeader
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.Text
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.compose.navigation.navigateToDetail
import com.github.droibit.oss_licenses.ui.viewmodel.OssLicenseViewModel
import com.github.droibit.oss_licenses.ui.wear.compose.material3.R

@Composable
internal fun OssLicenseListScreen(
  navController: NavController,
  viewModel: OssLicenseViewModel,
  modifier: Modifier = Modifier,
  listState: ScalingLazyListState = rememberScalingLazyListState(),
) {
  Scaffold(
    modifier = modifier,
    // positionIndicator = {
    //   PositionIndicator(scalingLazyListState = listState)
    // },
  ) {
    val licenses by viewModel.licenses.collectAsState()
    OssLicenseList(
      licenses = licenses,
      modifier = Modifier
        .fillMaxSize(),
      listState = listState,
      onItemClick = { license ->
        navController.navigateToDetail(license.libraryName)
      },
    )
  }
}

@OptIn(ExperimentalWearFoundationApi::class)
@Composable
internal fun OssLicenseList(
  licenses: List<OssLicense>,
  listState: ScalingLazyListState,
  onItemClick: (OssLicense) -> Unit,
  modifier: Modifier = Modifier,
  focusRequester: FocusRequester = rememberActiveFocusRequester(),
) {
  ScalingLazyColumn(
    modifier = modifier
      .rotaryScrollable(focusRequester, listState),
    state = listState,
  ) {
    if (licenses.isNotEmpty()) {
      item {
        ListHeader {
          Text(
            text = stringResource(id = R.string.oss_licenses_title),
            textAlign = TextAlign.Center,
          )
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
  license: OssLicense,
  modifier: Modifier = Modifier,
  onClick: () -> Unit = {},
) {
  Button(
    onClick = onClick,
    modifier = modifier.fillMaxWidth(),
    colors = ButtonDefaults.buttonColors(
      containerColor = MaterialTheme.colorScheme.surface,
      contentColor = MaterialTheme.colorScheme.onSurface,
    ),
    label = {
      Text(text = license.libraryName)
    },
  )
}
