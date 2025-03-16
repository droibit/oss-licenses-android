package com.github.droibit.oss_licenses.ui.compose.material3.internal

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.github.droibit.oss_licenses.ui.OssLicenseUiState
import com.github.droibit.oss_licenses.ui.compose.material3.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
internal fun OssLicensesTopAppBar(
  navigator: ThreePaneScaffoldNavigator<OssLicenseUiState>,
  modifier: Modifier = Modifier,
  onBack: () -> Unit = {},
) {
  CenterAlignedTopAppBar(
    title = {
      Text(
        text = navigator.currentDestination?.content?.library?.takeIf { navigator.isSinglePane() }
          ?: stringResource(id = R.string.oss_licenses_title),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
      )
    },
    navigationIcon = {
      BackNavigationButton(onClick = onBack)
    },
    modifier = modifier,
  )
}

@Composable
internal fun BackNavigationButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit = {},
) {
  IconButton(
    onClick = onClick,
    modifier = modifier,
  ) {
    Icon(
      imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
      contentDescription = "Back",
    )
  }
}
