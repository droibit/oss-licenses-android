package com.github.droibit.oss_licenses.ui.wearable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.wear.widget.SwipeDismissFrameLayout
import com.github.droibit.oss_licenses.parser.OssLicense

internal class OssLicenseFragment : Fragment() {

  private lateinit var swipeDismissLayout: SwipeDismissFrameLayout

  private val swipeDismissCallback = object : SwipeDismissFrameLayout.Callback() {
    override fun onDismissed(layout: SwipeDismissFrameLayout) {
      // Prevent flicker on screen.
      layout.visibility = View.INVISIBLE
      requireFragmentManager().popBackStack()
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val inflatedView = inflater.inflate(R.layout.fragment_oss_license, container, false)
    return SwipeDismissFrameLayout(requireContext()).apply {
      addView(inflatedView)
      addCallback(swipeDismissCallback)
    }
        .also {
          this.swipeDismissLayout = it
        }
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    val ossLicense = requireNotNull(arguments).getSerializable(ARG_OSS_LICENSE) as OssLicense
    view.findViewById<TextView>(R.id.oss_name)
        .text = ossLicense.libraryName
    view.findViewById<TextView>(R.id.oss_license)
        .text = ossLicense.license
  }

  override fun onDestroyView() {
    swipeDismissLayout.removeCallback(swipeDismissCallback)
    super.onDestroyView()
  }

  companion object {

    private const val ARG_OSS_LICENSE = "ARG_OSS_LICENSE"

    fun newInstance(ossLicense: OssLicense) = OssLicenseFragment().apply {
      arguments = Bundle(1).also {
        it.putSerializable(ARG_OSS_LICENSE, ossLicense)
      }
    }
  }
}