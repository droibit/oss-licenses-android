package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.compose.foundation.lazy.TransformingLazyColumnState
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.ButtonDefaults
import androidx.wear.compose.material3.ListHeader
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.Text
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.wear.compose.material3.R

@Composable
internal fun OssLicenseListScreen(
  licenses: List<OssLicense>,
  modifier: Modifier = Modifier,
  listState: TransformingLazyColumnState = rememberTransformingLazyColumnState(),
  onNavigateToDetail: (OssLicense) -> Unit = {},
) {
  ScreenScaffold(
    scrollState = listState,
    modifier = modifier,
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
  listState: TransformingLazyColumnState,
  modifier: Modifier = Modifier,
  onItemClick: (OssLicense) -> Unit = {},
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
  Button(
    onClick = {
      onClick(license)
    },
    modifier = modifier.fillMaxWidth(),
    colors = ButtonDefaults.buttonColors(
      containerColor = MaterialTheme.colorScheme.surfaceContainer,
      contentColor = MaterialTheme.colorScheme.onSurface,
    ),
    label = {
      Text(
        text = license.library,
        textAlign = TextAlign.Start,
      )
    },
  )
}
