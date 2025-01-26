package com.github.droibit.oss_licenses.ui.compose.material3.internal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.compose.material3.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OssLicenseListScreen(
  licenses: List<OssLicense>,
  modifier: Modifier = Modifier,
  onNavigateBack: () -> Unit = {},
  onNavigateToDetail: (OssLicense) -> Unit = {},
) {
  Scaffold(
    modifier = modifier,
    topBar = {
      TopAppBar(
        title = {
          Text(text = stringResource(id = R.string.oss_licenses_title))
        },
        navigationIcon = {
          BackNavigationButton(onClick = onNavigateBack)
        },
      )
    },
  ) { innerPadding ->
    OssLicenseList(
      licenses = licenses,
      modifier = Modifier
        .padding(innerPadding)
        .fillMaxSize(),
      onItemClick = onNavigateToDetail,
    )
  }
}

@Composable
private fun OssLicenseList(
  licenses: List<OssLicense>,
  onItemClick: (OssLicense) -> Unit,
  modifier: Modifier = Modifier,
) {
  LazyColumn(
    modifier = modifier,
  ) {
    items(
      licenses,
      key = OssLicense::libraryName,
    ) { license ->
      OssLicenseItem(
        license = license,
        onClick = onItemClick,
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
  modifier: Modifier = Modifier,
  onClick: (OssLicense) -> Unit = {},
) {
  ListItem(
    headlineContent = {
      Text(text = license.libraryName)
    },
    modifier = modifier.clickable(
      onClick = {
        onClick(license)
      },
    ),
  )
}
