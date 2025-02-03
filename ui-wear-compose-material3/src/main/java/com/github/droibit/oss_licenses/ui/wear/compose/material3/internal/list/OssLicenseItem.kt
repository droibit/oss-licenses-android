package com.github.droibit.oss_licenses.ui.wear.compose.material3.internal.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.ButtonDefaults
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.Text
import com.github.droibit.oss_licenses.parser.OssLicense

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
