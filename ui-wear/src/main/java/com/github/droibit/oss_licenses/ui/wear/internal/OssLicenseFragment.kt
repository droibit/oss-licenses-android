package com.github.droibit.oss_licenses.ui.wear.internal

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.wear.widget.SwipeDismissFrameLayout
import com.github.droibit.oss_licenses.ui.OssLicenseUiState
import com.github.droibit.oss_licenses.ui.wear.R

private const val ARG_OSS_LICENSE = "ARG_OSS_LICENSE"

internal class OssLicenseFragment : Fragment(R.layout.fragment_oss_license) {
  private val swipeDismissCallback = object : SwipeDismissFrameLayout.Callback() {
    override fun onDismissed(layout: SwipeDismissFrameLayout) {
      // Prevent flicker on screen.
      layout.visibility = View.INVISIBLE
      parentFragmentManager.popBackStack()
    }
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?,
  ) {
    super.onViewCreated(view, savedInstanceState)

    (view as SwipeDismissFrameLayout).addCallback(swipeDismissCallback)

    val ossLicense = requireNotNull(
      requireArguments().getParcelable<OssLicenseUiState>(ARG_OSS_LICENSE),
    )
    view
      .findViewById<TextView>(R.id.oss_name)
      .text = ossLicense.library
    view
      .findViewById<TextView>(R.id.oss_license)
      .text = ossLicense.licenseText
  }

  override fun onDestroyView() {
    (view as SwipeDismissFrameLayout).removeCallback(swipeDismissCallback)
    super.onDestroyView()
  }

  companion object {
    fun newInstance(ossLicense: OssLicenseUiState) = OssLicenseFragment().apply {
      arguments = bundleOf(
        ARG_OSS_LICENSE to ossLicense,
      )
    }
  }
}
