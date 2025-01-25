package com.github.droibit.oss_licenses.ui.viewmodel

import android.content.Context
import androidx.annotation.RestrictTo
import androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP
import androidx.annotation.UiThread
import androidx.lifecycle.ViewModel
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
  private val parser: OssLicenseParser,
  private val dispatcher: CoroutineDispatcher,
  private val licensesSink: MutableStateFlow<List<OssLicense>>,
) : ViewModel() {

  val licenses: StateFlow<List<OssLicense>>
    get() = licensesSink

  @Suppress("unused")
  constructor() : this(
    OssLicenseParser(),
    Dispatchers.IO,
    licensesSink = MutableStateFlow(emptyList()),
  )

  fun loadLicenses(context: Context) {
    viewModelScope.launch {
      if (licensesSink.value.isNotEmpty()) {
        return@launch
      }
      licensesSink.value = withContext(dispatcher) {
        parser.parse(context)
      }
    }
  }

  @UiThread
  fun getLicense(name: String): OssLicense {
    return licenses.value.first { it.libraryName == name }
  }
}
