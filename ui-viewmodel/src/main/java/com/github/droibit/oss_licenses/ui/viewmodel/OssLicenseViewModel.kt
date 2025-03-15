package com.github.droibit.oss_licenses.ui.viewmodel

import android.content.Context
import androidx.annotation.RestrictTo
import androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP
import androidx.annotation.UiThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.parser.OssLicenseParser
import com.github.droibit.oss_licenses.ui.OssLicenseUiState
import java.util.UUID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel that manages the state and operations of third-party library licenses.
 *
 * The ViewModel provides functionality to:
 * - Load and cache licenses from application assets
 * - Expose license list as observable state
 * - Retrieve individual license details
 *
 * @property parser The parser used to read license information from assets
 * @property licensesSink The mutable state holding the list of third-party library licenses UI states
 */
@RestrictTo(LIBRARY_GROUP)
class OssLicenseViewModel internal constructor(
  private val parser: OssLicenseParser,
  private val licensesSink: MutableStateFlow<List<OssLicenseUiState>>,
  private val licenseIdGenerator: (OssLicense) -> String,
) : ViewModel() {
  /**
   * A [StateFlow] that emits the list of third-party library licenses UI states.
   *
   * The initial value is an empty list.
   */
  val licenses: StateFlow<List<OssLicenseUiState>>
    get() = licensesSink

  @Suppress("unused")
  constructor() : this(
    OssLicenseParser(),
    licensesSink = MutableStateFlow(emptyList()),
    licenseIdGenerator = { UUID.randomUUID().toString() },
  )

  /**
   * Loads the third-party library licenses from the application's assets.
   * If licenses are already loaded, this method will return immediately.
   */
  fun loadLicenses(context: Context) {
    viewModelScope.launch {
      if (licensesSink.value.isNotEmpty()) {
        return@launch
      }
      licensesSink.value = parser.parse(context).map {
        OssLicenseUiState(
          id = licenseIdGenerator(it),
          library = it.library,
          licenseText = it.text,
        )
      }
    }
  }

  /**
   * Returns the [OssLicenseUiState] for the specified id.
   *
   * @param id The id of the third-party library license to retrieve.
   * @return The [OssLicenseUiState] for the specified id.
   */
  @UiThread
  fun getLicense(id: String): OssLicenseUiState {
    check(licenses.value.isNotEmpty()) { "Licenses have not been loaded yet." }
    return licenses.value.first { it.id == id }
  }
}
