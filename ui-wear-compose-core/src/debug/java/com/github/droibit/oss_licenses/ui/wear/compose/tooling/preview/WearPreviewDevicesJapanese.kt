package com.github.droibit.oss_licenses.ui.wear.compose.tooling.preview

import androidx.annotation.RestrictTo
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.tooling.preview.devices.WearDevices

// It ensures consistency with multiple preview names for Wear OS.
@Suppress("ktlint:compose:preview-annotation-naming")
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
@Preview(
  name = "Large Round(Japanese)",
  device = WearDevices.LARGE_ROUND,
  backgroundColor = 0xff000000,
  showBackground = true,
  group = "Devices - Large Round",
  locale = "ja",
  showSystemUi = true,
)
@Preview(
  name = "Small Round(Japanese)",
  device = WearDevices.SMALL_ROUND,
  backgroundColor = 0xff000000,
  showBackground = true,
  group = "Devices - Small Round",
  locale = "ja",
  showSystemUi = true,
)
annotation class WearPreviewDevicesJapanese
