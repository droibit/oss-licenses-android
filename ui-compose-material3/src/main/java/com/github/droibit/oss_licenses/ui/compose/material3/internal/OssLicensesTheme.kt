package com.github.droibit.oss_licenses.ui.compose.material3.internal

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
internal fun OssLicensesTheme(
  content: @Composable () -> Unit,
) {
  val colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
  MaterialTheme(colorScheme = colorScheme) {
    content()
  }
}
