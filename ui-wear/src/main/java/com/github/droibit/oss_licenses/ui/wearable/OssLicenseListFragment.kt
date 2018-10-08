package com.github.droibit.oss_licenses.ui.wearable

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

internal class OssLicenseListFragment : Fragment(), CoroutineScope {

  private val job = Job()

  private val ossLicenses = MutableLiveData<List<OssLicense>>()

  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Default + job

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_oss_license_list, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    val adapter = OssLicenseListAdapter(requireContext()) {
      requireFragmentManager().beginTransaction()
          .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
          .add(R.id.oss_licenses_content, OssLicenseFragment.newInstance(ossLicense = it))
          .addToBackStack(null)
          .commit()
    }
    view.findViewById<RecyclerView>(R.id.oss_licenses_list)
        .also {
          it.adapter = adapter
          it.setHasFixedSize(true)
        }
    ossLicenses.observe(this, Observer {
      adapter.update(it)
    })
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    val ignoreLibraries = requireNotNull(arguments!!.getStringArrayList(ARG_IGNORE_LIBRARIES))
    launch {
      val parsedOssLicenses = OssLicenseParser.parse(requireContext(), ignoreLibraries)
      ossLicenses.postValue(parsedOssLicenses)
    }
  }

  override fun onDestroy() {
    job.cancel()
    super.onDestroy()
  }

  companion object {

    private const val ARG_IGNORE_LIBRARIES = "ARG_IGNORE_LIBRARIES"

    fun newInstance(ignoreLibraries: ArrayList<String>) = OssLicenseListFragment().apply {
      arguments = Bundle(1).also {
        it.putStringArrayList(ARG_IGNORE_LIBRARIES, ignoreLibraries)
      }
    }
  }
}

private class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val text: TextView = itemView.findViewById(android.R.id.text1)
}

private class OssLicenseListAdapter(
  context: Context,
  private val onItemClickListener: (OssLicense) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

  private val inflater = LayoutInflater.from(context)

  private val ossLicenses = mutableListOf<OssLicense>()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ViewHolder = ViewHolder(
      itemView = inflater.inflate(R.layout.list_item_oss_license, parent, false)
  ).also { vh ->
    vh.itemView.setOnClickListener {
      onItemClickListener.invoke(ossLicenses[vh.adapterPosition])
    }
  }

  override fun getItemCount(): Int = ossLicenses.size

  override fun onBindViewHolder(
    holder: ViewHolder,
    position: Int
  ) {
    holder.text.text = ossLicenses[position].libraryName
  }

  fun update(ossLicenses: List<OssLicense>) {
    this.ossLicenses.clear()
    this.ossLicenses.addAll(ossLicenses)
    this.notifyDataSetChanged()
  }
}
