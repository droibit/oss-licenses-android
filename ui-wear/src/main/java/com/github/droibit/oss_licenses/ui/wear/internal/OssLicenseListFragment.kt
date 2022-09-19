package com.github.droibit.oss_licenses.ui.wear.internal

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.github.droibit.oss_licenses.ui.viewmodel.OssLicenseViewModel
import com.github.droibit.oss_licenses.ui.wear.R
import kotlinx.coroutines.launch

internal class OssLicenseListFragment : Fragment(R.layout.fragment_oss_license_list) {
  private val viewModel: OssLicenseViewModel by activityViewModels()

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

    viewLifecycleOwner.lifecycleScope.launch {
      viewModel.licenses.flowWithLifecycle(viewLifecycleOwner.lifecycle)
        .collect {
          adapter.submitList(it)
        }
    }
  }

  companion object {

    fun newInstance() = OssLicenseListFragment()
  }
}
