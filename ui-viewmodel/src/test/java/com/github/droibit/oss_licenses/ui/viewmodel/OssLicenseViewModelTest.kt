package com.github.droibit.oss_licenses.ui.viewmodel

import android.content.Context
import app.cash.turbine.test
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.parser.OssLicenseParser
import com.github.droibit.oss_licenses.ui.OssLicenseUiState
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class OssLicenseViewModelTest {
  @get:Rule
  val mockKRule = MockKRule(this)

  @MockK
  private lateinit var parser: OssLicenseParser

  @MockK
  private lateinit var context: Context

  private lateinit var licensesSink: MutableStateFlow<List<OssLicenseUiState>>

  private lateinit var licenseIdGenerator: (OssLicense) -> String

  private lateinit var viewModel: OssLicenseViewModel

  @Before
  fun setUp() {
    licensesSink = MutableStateFlow(emptyList())
    licenseIdGenerator = { ossLicense ->
      ossLicense.library + ossLicense.text
    }
    viewModel = OssLicenseViewModel(parser, licensesSink, licenseIdGenerator)
  }

  @Test
  fun licenses_notifyValues() = runTest {
    viewModel.licenses.test {
      assertThat(awaitItem()).isEmpty()

      val licenses = listOf(
        OssLicenseUiState("id1", "library1", "license1"),
        OssLicenseUiState("id2", "library2", "license2"),
      )
      licensesSink.update { licenses }

      assertThat(awaitItem()).isEqualTo(licenses)
    }
  }

  @Test
  fun loadLicenses_success() = runTest {
    val licenses = listOf(
      OssLicense("library1", "license1"),
      OssLicense("library2", "license2"),
    )
    coEvery { parser.parse(context) } returns licenses

    licensesSink.test {
      assertThat(awaitItem()).isEmpty()

      viewModel.loadLicenses(context)

      val actualLicenses = licenses.map {
        OssLicenseUiState(
          id = licenseIdGenerator(it),
          library = it.library,
          licenseText = it.text,
        )
      }
      assertThat(awaitItem()).isEqualTo(actualLicenses)
    }
  }

  @Test
  fun loadLicenses_skip() = runTest {
    val licenses = listOf(
      OssLicenseUiState("id1", "library1", "license1"),
      OssLicenseUiState("id2", "library2", "license2"),
    )
    licensesSink.update { licenses }

    licensesSink.test {
      assertThat(awaitItem()).isEqualTo(licenses)

      viewModel.loadLicenses(context)

      expectNoEvents()
    }
  }

  @Test
  fun getLicense_success() {
    val license1 = OssLicenseUiState("id1", "library1", "license1")
    val license2 = OssLicenseUiState("id2", "library2", "license2")
    licensesSink.update {
      listOf(license1, license2)
    }

    assertThat(viewModel.getLicense("id1")).isEqualTo(license1)
    assertThat(viewModel.getLicense("id2")).isEqualTo(license2)
  }

  @Test(expected = IllegalStateException::class)
  fun getLicense_notLoaded() {
    viewModel.getLicense("license")
  }
}
