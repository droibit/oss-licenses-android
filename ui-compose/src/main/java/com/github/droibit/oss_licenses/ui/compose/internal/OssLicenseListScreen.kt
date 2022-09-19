package com.github.droibit.oss_licenses.ui.compose.internal

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.compose.R
import com.github.droibit.oss_licenses.ui.compose.internal.OssLicenseGraph.Directions.toDetail
import com.github.droibit.oss_licenses.ui.compose.internal.OssLicenseGraph.ROUTE_LIST

@Composable
internal fun OssLicenseListScreen(
  navController: NavController,
  modifier: Modifier = Modifier,
  viewModel: OssLicenseViewModel,
) {
  val context = LocalContext.current
  Scaffold(
    modifier = modifier,
    topBar = {
      TopAppBar(
        title = {
          Text(text = stringResource(id = R.string.oss_licenses_title))
        },
        navigationIcon = {
          IconButton(
            onClick = {
              (context as? Activity)?.finish()
            },
          ) {
            Icon(
              imageVector = Icons.Outlined.ArrowBack,
              contentDescription = null,
            )
          }
        },
      )
    },
  ) { innerPadding ->
    OssLicenseList(
      licenses = viewModel.licenses,
      modifier = Modifier
        .padding(innerPadding)
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
private fun OssLicenseList(
  licenses: List<OssLicense>,
  modifier: Modifier = Modifier,
  onItemClick: (OssLicense) -> Unit = {},
) {
  LazyColumn(
    modifier = modifier,
  ) {
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
      Divider()
    }
  }
}

@Composable
private fun OssLicenseItem(
  modifier: Modifier = Modifier,
  license: OssLicense,
  onClick: () -> Unit = {},
) {
  ListItem(
    headlineText = {
      Text(text = license.libraryName)
    },
    modifier = modifier.clickable(onClick = onClick),
  )
}
