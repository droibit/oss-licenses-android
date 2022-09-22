package com.github.droibit.oss_licenses.ui.viewmodel

import android.app.Application
import androidx.annotation.RestrictTo
import androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP
import androidx.annotation.UiThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.parser.OssLicenseParser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@RestrictTo(LIBRARY_GROUP)
class OssLicenseViewModel(
  application: Application,
  private val parser: OssLicenseParser,
  private val ignoreLibraries: Set<String>,
  private val dispatcher: CoroutineDispatcher,
  private val licensesSink: MutableStateFlow<List<OssLicense>>,
) : AndroidViewModel(application) {

  val licenses: StateFlow<List<OssLicense>>
    get() = licensesSink

  @Suppress("unused")
  constructor(
    application: Application,
    savedStateHandle: SavedStateHandle,
  ) : this(
    application,
    OssLicenseParser(application),
    savedStateHandle.getStringSet(EXTRA_IGNORE_LIBRARIES),
    Dispatchers.IO,
    licensesSink = MutableStateFlow(emptyList()),
  )

  fun ensureLicenses() {
    viewModelScope.launch {
      if (licensesSink.value.isNotEmpty()) {
        return@launch
      }
      licensesSink.value = withContext(dispatcher) {
        parser.parse(ignoreLibraries)
      }
    }
  }

  @UiThread
  fun getLicense(name: String): OssLicense {
    return licenses.value.first { it.libraryName == name }
  }

  companion object {
    const val EXTRA_IGNORE_LIBRARIES =
      "com.github.droibit.oss_licenses.ui.viewomdel.EXTRA_IGNORE_LIBRARIES"
  }
}

private fun SavedStateHandle.getStringSet(key: String): Set<String> {
  val value = get<List<String>>(key)
  return value?.toSet() ?: emptySet()
}
