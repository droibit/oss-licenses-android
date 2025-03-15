package com.github.droibit.oss_licenses.ui

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RestrictTo
import androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP

/**
 * Represents the UI state for displaying a third-party library license.
 *
 * @property id The unique identifier of the UI state.
 * @property library The name of the third-party library.
 * @property licenseText The license of the third-party library.
 */
@RestrictTo(LIBRARY_GROUP)
data class OssLicenseUiState(
  val id: String,
  val library: String,
  val licenseText: String,
) : Parcelable {
  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(id)
    parcel.writeString(library)
    parcel.writeString(licenseText)
  }

  override fun describeContents(): Int = 0

  companion object CREATOR : Parcelable.Creator<OssLicenseUiState?> {
    override fun createFromParcel(source: Parcel?): OssLicenseUiState? {
      val id = source?.readString() ?: return null
      val library = source.readString() ?: return null
      val license = source.readString() ?: return null
      return OssLicenseUiState(id, library, license)
    }

    override fun newArray(size: Int): Array<OssLicenseUiState?> = arrayOfNulls(size)
  }
}
