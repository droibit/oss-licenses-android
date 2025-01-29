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
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.compose.material3.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
internal fun OssLicensesTopAppBar(
  navigator: ThreePaneScaffoldNavigator<OssLicense>,
  modifier: Modifier = Modifier,
  onBack: () -> Unit = {},
) {
  CenterAlignedTopAppBar(
    title = {
      Text(
        text = if (navigator.isSinglePane()) {
          navigator.currentDestination?.content?.libraryName
            ?: stringResource(id = R.string.oss_licenses_title)
        } else {
          stringResource(id = R.string.oss_licenses_title)
        },
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
      contentDescription = null,
    )
  }
}
