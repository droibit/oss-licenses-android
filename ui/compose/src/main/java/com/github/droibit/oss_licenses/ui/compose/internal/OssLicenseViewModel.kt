package com.github.droibit.oss_licenses.ui.compose.internal

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.parser.OssLicenseParser
import com.github.droibit.oss_licenses.ui.compose.OssLicensesActivity.Companion.EXTRA_IGNORE_LIBRARIES
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OssLicenseViewModel(
  application: Application,
  private val parser: OssLicenseParser,
  private val ignoreLibraries: Set<String>,
  private val dispatcher: CoroutineDispatcher,
) : AndroidViewModel(application) {

  var licenses by mutableStateOf<List<OssLicense>>(emptyList())
    private set

  constructor(
    application: Application,
    savedStateHandle: SavedStateHandle,
  ) : this(
    application,
    OssLicenseParser(application),
    savedStateHandle.getAsSet(EXTRA_IGNORE_LIBRARIES),
    Dispatchers.IO,
  )

  fun ensureLicenses() {
    viewModelScope.launch {
      licenses = withContext(dispatcher) {
        parser.parse(ignoreLibraries)
      }
    }
  }

  fun getLicense(name: String): OssLicense {
    return licenses.first { it.libraryName == name }
  }
}

private fun SavedStateHandle.getAsSet(key: String): Set<String> {
  val value = get<List<String>>(EXTRA_IGNORE_LIBRARIES)
  return value?.toSet() ?: emptySet()
}
