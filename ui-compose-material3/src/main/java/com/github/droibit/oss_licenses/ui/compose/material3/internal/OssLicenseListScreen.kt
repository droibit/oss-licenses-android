package com.github.droibit.oss_licenses.ui.compose.material3.internal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.compose.OssLicenseCollection
import com.github.droibit.oss_licenses.ui.compose.material3.R
import com.github.droibit.oss_licenses.ui.navigation.compose.navigateToDetail

@Composable
internal fun OssLicenseListScreen(
  licenses: State<List<OssLicense>>,
  navController: NavController,
  modifier: Modifier = Modifier,
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
    OssLicenseList(
      licenses = OssLicenseCollection(licenses.value),
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
  licenses: OssLicenseCollection,
  onItemClick: (OssLicense) -> Unit,
  modifier: Modifier = Modifier,
) {
  LazyColumn(
    modifier = modifier,
  ) {
    items(
      licenses(),
      key = { it.libraryName },
    ) { license ->
      OssLicenseItem(
        license = license,
        onClick = {
          onItemClick(license)
        },
      )
      HorizontalDivider(
        modifier = Modifier.padding(horizontal = 16.dp),
      )
    }
  }
}

@Composable
private fun OssLicenseItem(
  license: OssLicense,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  ListItem(
    headlineContent = {
      Text(text = license.libraryName)
    },
    modifier = modifier.clickable(onClick = onClick),
  )
}
