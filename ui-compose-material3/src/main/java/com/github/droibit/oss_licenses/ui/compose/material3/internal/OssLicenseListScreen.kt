package com.github.droibit.oss_licenses.ui.compose.material3.internal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.compose.material3.R
import com.github.droibit.oss_licenses.ui.compose.navigation.navigateToDetail
import com.github.droibit.oss_licenses.ui.viewmodel.OssLicenseViewModel

@Composable
internal fun OssLicenseListScreen(
  navController: NavController,
  modifier: Modifier = Modifier,
  viewModel: OssLicenseViewModel,
) {
  Scaffold(
    modifier = modifier,
    topBar = {
      TopAppBar(
        title = {
          Text(text = stringResource(id = R.string.oss_licenses_title))
        },
        navigationIcon = {
          BackNavigationButton(navController)
        },
      )
    },
  ) { innerPadding ->
    val licenses by viewModel.licenses.collectAsState()
    OssLicenseList(
      licenses = licenses,
      modifier = Modifier
        .padding(innerPadding)
        .fillMaxSize(),
      onItemClick = { license ->
        navController.navigateToDetail(license.libraryName)
      },
    )
  }
}

@Composable
private fun OssLicenseList(
  licenses: List<OssLicense>,
  modifier: Modifier = Modifier,
  onItemClick: (OssLicense) -> Unit,
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
  onClick: () -> Unit,
) {
  ListItem(
    headlineContent = {
      Text(text = license.libraryName)
    },
    modifier = modifier.clickable(onClick = onClick),
  )
}
