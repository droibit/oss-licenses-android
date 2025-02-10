package com.github.droibit.oss_licenses.ui.viewmodel

import android.content.Context
import app.cash.turbine.test
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.parser.OssLicenseParser
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

  private lateinit var licensesSink: MutableStateFlow<List<OssLicense>>

  private lateinit var viewModel: OssLicenseViewModel

  @Before
  fun setUp() {
    licensesSink = MutableStateFlow(emptyList())
    viewModel = OssLicenseViewModel(parser, licensesSink)
  }

  @Test
  fun licenses_notifyValues() = runTest {
    viewModel.licenses.test {
      assertThat(awaitItem()).isEmpty()

      val licenses = listOf(
        OssLicense("library1", "license1"),
        OssLicense("library2", "license2"),
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

      assertThat(awaitItem()).isEqualTo(licenses)
    }
  }

  @Test
  fun loadLicenses_skip() = runTest {
    val licenses = listOf(
      OssLicense("library1", "license1"),
      OssLicense("library2", "license2"),
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
    val license1 = OssLicense("library1", "license1")
    val license2 = OssLicense("library2", "license2")
    licensesSink.update {
      listOf(license1, license2)
    }

    assertThat(viewModel.getLicense("library1")).isEqualTo(license1)
    assertThat(viewModel.getLicense("library2")).isEqualTo(license2)
  }

  @Test(expected = IllegalStateException::class)
  fun getLicense_notLoaded() {
    viewModel.getLicense("library1")
  }
}
