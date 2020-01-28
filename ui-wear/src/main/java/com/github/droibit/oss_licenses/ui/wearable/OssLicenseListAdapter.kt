package com.github.droibit.oss_licenses.ui.wearable

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.wearable.R.layout

internal class OssLicenseListAdapter(
  context: Context,
  private val onItemClickListener: (OssLicense) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

  private val inflater: LayoutInflater =
    LayoutInflater.from(context)

  private val ossLicenses = mutableListOf<OssLicense>()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ViewHolder = ViewHolder(
      itemView = inflater.inflate(
          layout.list_item_oss_license,
          parent, false
      )
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

internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val text: TextView = itemView.findViewById(android.R.id.text1)
}
