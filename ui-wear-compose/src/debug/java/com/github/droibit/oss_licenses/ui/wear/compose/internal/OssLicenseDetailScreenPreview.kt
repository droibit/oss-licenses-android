package com.github.droibit.oss_licenses.ui.wear.compose.internal

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.MaterialTheme
import com.github.droibit.oss_licenses.parser.OssLicense

@Preview(
  device = Devices.WEAR_OS_LARGE_ROUND,
  showSystemUi = true,
  backgroundColor = 0xff000000,
  showBackground = true,
  group = "Devices - Large Round",
)
@Preview(
  device = Devices.WEAR_OS_SQUARE,
  showSystemUi = true,
  backgroundColor = 0xff000000,
  showBackground = true,
  group = "Devices - Square",
)
@Composable
fun OssLicenseDetailScreenPreview() {
  MaterialTheme {
    OssLicenseDetailScreen(
      license = OssLicense(
        libraryName = "OSS Licenses",
        license = "https://www.apache.org/licenses/LICENSE-2.0.txt",
      ),
    )
  }
}
