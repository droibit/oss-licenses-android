package com.github.droibit.oss_licenses.ui.compose.internal

import android.app.Activity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
internal fun BackNavigationButton(
  navController: NavController,
  modifier: Modifier = Modifier,
) {
  val context = LocalContext.current
  IconButton(
    onClick = {
      if (!navController.popBackStack()) {
        (context as? Activity)?.finish()
      }
    },
    modifier = modifier,
  ) {
    Icon(
      imageVector = Icons.Outlined.ArrowBack,
      contentDescription = null,
    )
  }
}
