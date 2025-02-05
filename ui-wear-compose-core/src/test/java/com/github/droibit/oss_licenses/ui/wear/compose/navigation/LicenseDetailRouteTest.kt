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
  fun arguments_containsLibraryNameArgument() {
    val arguments = LicenseDetail.arguments
    assertThat(arguments).hasSize(1)
    assertThat(arguments[0].name).isEqualTo("library_name")
    assertThat(arguments[0].argument.type).isEqualTo(NavType.StringType)
  }

  @Test
  fun toDetail_encodesLibraryName() {
    val libraryName = "Test Library"
    val expectedEncodedName = Base64.encodeToString(
      libraryName.toByteArray(),
      Base64.URL_SAFE or Base64.NO_WRAP,
    )
    val expectedRoute = "license_detail?library_name=$expectedEncodedName"

    val actual = LicenseDetail.toDetail(libraryName)
    assertThat(actual).isEqualTo(expectedRoute)
  }

  @Test
  fun getLibraryName_decodesLibraryName() {
    val libraryName = "Test Library"
    val encodedName = Base64.encodeToString(
      libraryName.toByteArray(),
      Base64.URL_SAFE or Base64.NO_WRAP,
    )
    val bundle = bundleOf("library_name" to encodedName)
    val actual = LicenseDetail.getLibraryName(bundle)
    assertThat(actual).isEqualTo(libraryName)
  }
}
