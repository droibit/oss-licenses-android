package com.github.droibit.oss_licenses.ui.viewmodel

import android.content.Context
import androidx.annotation.RestrictTo
import androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP
import androidx.annotation.UiThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.parser.OssLicenseParser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel that manages the state and operations of open source licenses.
 *
 * The ViewModel provides functionality to:
 * - Load and cache licenses from application assets
 * - Expose license list as observable state
 * - Retrieve individual license details
 *
 * @property parser The parser used to read license information from assets
 * @property licensesSink The mutable state holding the list of licenses
 */
@RestrictTo(LIBRARY_GROUP)
class OssLicenseViewModel(
  private val parser: OssLicenseParser,
  private val licensesSink: MutableStateFlow<List<OssLicense>>,
) : ViewModel() {
  /**
   * A [StateFlow] that emits the list of open source licenses.
   *
   * The initial value is an empty list.
   */
  val licenses: StateFlow<List<OssLicense>>
    get() = licensesSink

  @Suppress("unused")
  constructor() : this(
    OssLicenseParser(),
    licensesSink = MutableStateFlow(emptyList()),
  )

  /**
   * Loads the open source licenses from the application's assets.
   * If licenses are already loaded, this method will return immediately.
   */
  fun loadLicenses(context: Context) {
    viewModelScope.launch {
      if (licensesSink.value.isNotEmpty()) {
        return@launch
      }
      licensesSink.value = parser.parse(context)
    }
  }

  /**
   * Returns the [OssLicense] for the specified library name.
   *
   * @param name The name of the library to get the license for.
   * @return The [OssLicense] for the specified library.
   */
  @UiThread
  fun getLicense(name: String): OssLicense {
    check(licenses.value.isNotEmpty()) { "Licenses have not been loaded yet." }
    return licenses.value.first { it.library == name }
  }
}
