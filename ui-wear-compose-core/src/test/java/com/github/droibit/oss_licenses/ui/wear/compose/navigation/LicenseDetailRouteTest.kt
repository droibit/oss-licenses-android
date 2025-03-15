package com.github.droibit.oss_licenses.ui.wear.compose.navigation

import android.util.Base64
import androidx.core.os.bundleOf
import androidx.navigation.NavType
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.droibit.oss_licenses.ui.wear.compose.navigation.Routes.LicenseDetail
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class LicenseDetailRouteTest {
  @Test
  fun arguments_containsIdArgument() {
    val arguments = LicenseDetail.arguments
    assertThat(arguments).hasSize(1)
    assertThat(arguments[0].name).isEqualTo("license_id")
    assertThat(arguments[0].argument.type).isEqualTo(NavType.StringType)
  }

  @Test
  fun toDetail_encodesId() {
    val licenseId = "licenseId"
    val expectedEncodedName = Base64.encodeToString(
      licenseId.toByteArray(),
      Base64.URL_SAFE or Base64.NO_WRAP,
    )
    val expectedRoute = "license_detail?license_id=$expectedEncodedName"

    val actual = LicenseDetail.toDetail(licenseId)
    assertThat(actual).isEqualTo(expectedRoute)
  }

  @Test
  fun getLicenseId_decodesLicenseId() {
    val licenseId = "license-1"
    val encodedName = Base64.encodeToString(
      licenseId.toByteArray(),
      Base64.URL_SAFE or Base64.NO_WRAP,
    )
    val bundle = bundleOf("license_id" to encodedName)
    val actual = LicenseDetail.getLicenseId(bundle)
    assertThat(actual).isEqualTo(licenseId)
  }
}
