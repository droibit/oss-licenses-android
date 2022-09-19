package com.github.droibit.oss_licenses.ui.wearable

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.parser.OssLicenseParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ARG_IGNORE_LIBRARIES = "ARG_IGNORE_LIBRARIES"

internal class OssLicenseListFragment : Fragment(R.layout.fragment_oss_license_list) {

  private val ossLicenses = MutableLiveData<List<OssLicense>>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val ignoreLibraries =
      requireNotNull(requireArguments().getStringArrayList(ARG_IGNORE_LIBRARIES))

    lifecycleScope.launch {
      @Suppress("BlockingMethodInNonBlockingContext")
      val parsedOssLicenses = withContext(Dispatchers.IO) {
        OssLicenseParser(requireContext()).parse(ignoreLibraries.toSet())
      }
      ossLicenses.postValue(parsedOssLicenses)
    }
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?,
  ) {
    super.onViewCreated(view, savedInstanceState)

    val adapter =
      OssLicenseListAdapter(
        requireContext(),
      ) {
        parentFragmentManager.beginTransaction()
          .setCustomAnimations(
            androidx.fragment.R.animator.fragment_close_enter,
            androidx.fragment.R.animator.fragment_open_exit,
            androidx.fragment.R.animator.fragment_fade_enter,
            androidx.fragment.R.animator.fragment_fade_exit,
          )
          .replace(
            R.id.oss_licenses_content,
            OssLicenseFragment.newInstance(ossLicense = it),
          )
          .addToBackStack(null)
          .commit()
      }
    view.findViewById<RecyclerView>(R.id.oss_licenses_list)
      .also {
        it.adapter = adapter
        it.setHasFixedSize(true)
      }
    ossLicenses.observe(viewLifecycleOwner) {
      adapter.submitList(it)
    }
  }

  companion object {

    fun newInstance(ignoreLibraries: List<String>) = OssLicenseListFragment().apply {
      arguments = Bundle(1).also {
        it.putStringArrayList(ARG_IGNORE_LIBRARIES, ArrayList(ignoreLibraries))
      }
    }
  }
}
