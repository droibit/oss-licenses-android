package com.github.droibit.oss_licenses.parser

import android.os.Parcel
import android.os.Parcelable

/**
 * Represents a third-party library and its license.
 *
 * @param library The name of the third-party library.
 * @param text The license of the third-party library.
 */
data class OssLicense(
  val library: String,
  val text: String,
) : Parcelable {
  override fun describeContents(): Int = 0

  override fun writeToParcel(dest: Parcel, flags: Int) {
    dest.writeString(library)
    dest.writeString(text)
  }

  companion object CREATOR : Parcelable.Creator<OssLicense?> {
    override fun createFromParcel(source: Parcel?): OssLicense? {
      val libraryName = source?.readString() ?: return null
      val license = source.readString() ?: return null
      return OssLicense(libraryName, license)
    }

    override fun newArray(size: Int): Array<OssLicense?> = arrayOfNulls(size)
  }
}
