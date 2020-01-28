package com.github.droibit.oss_licenses.ui.wearable

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.parser.OssLicenseParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

private const val ARG_IGNORE_LIBRARIES = "ARG_IGNORE_LIBRARIES"

class OssLicenseListFragment : Fragment(R.layout.fragment_oss_license_list),
    CoroutineScope {

  private val job = Job()

  private val ossLicenses = MutableLiveData<List<OssLicense>>()

  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Default + job

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    val adapter =
      OssLicenseListAdapter(
          requireContext()
      ) {

        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                androidx.fragment.R.anim.fragment_close_enter,
                androidx.fragment.R.anim.fragment_open_exit,
                androidx.fragment.R.anim.fragment_fade_enter,
                androidx.fragment.R.anim.fragment_fade_exit
            )
            .replace(R.id.oss_licenses_content, OssLicenseFragment.newInstance(ossLicense = it))
            .addToBackStack(null)
            .commit()
      }
    view.findViewById<RecyclerView>(R.id.oss_licenses_list)
        .also {
          it.adapter = adapter
          it.setHasFixedSize(true)
        }
    ossLicenses.observe(viewLifecycleOwner, Observer {
      adapter.update(it)
    })
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    val ignoreLibraries =
      requireNotNull(requireArguments().getStringArrayList(ARG_IGNORE_LIBRARIES))
    launch {
      @Suppress("BlockingMethodInNonBlockingContext")
      val parsedOssLicenses = OssLicenseParser.parse(requireContext(), ignoreLibraries)
      ossLicenses.postValue(parsedOssLicenses)
    }
  }

  override fun onDestroy() {
    job.cancel()
    super.onDestroy()
  }

  companion object {

    fun newInstance(ignoreLibraries: List<String>) = OssLicenseListFragment().apply {
      arguments = Bundle(1).also {
        it.putStringArrayList(ARG_IGNORE_LIBRARIES, ArrayList(ignoreLibraries))
      }
    }
  }
}
