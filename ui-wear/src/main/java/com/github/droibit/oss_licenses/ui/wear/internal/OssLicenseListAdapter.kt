package com.github.droibit.oss_licenses.ui.wear.internal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.wear.R

internal class OssLicenseListAdapter(
  context: Context,
  private val onItemClickListener: (OssLicense) -> Unit,
) : ListAdapter<OssLicense, ViewHolder>(DIFF_CALLBACK) {
  private val inflater: LayoutInflater = LayoutInflater.from(context)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
    itemView = inflater.inflate(
      R.layout.list_item_oss_license,
      parent,
      false,
    ),
  ).also { vh ->
    vh.itemView.setOnClickListener {
      onItemClickListener.invoke(getItem(vh.bindingAdapterPosition))
    }
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.text.text = getItem(position).library
  }

  companion object {
    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<OssLicense>() {
      override fun areItemsTheSame(
        oldItem: OssLicense,
        newItem: OssLicense,
      ): Boolean = oldItem.library == newItem.library

      override fun areContentsTheSame(
        oldItem: OssLicense,
        newItem: OssLicense,
      ): Boolean = oldItem == newItem
    }
  }
}

internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val text: TextView = itemView.findViewById(android.R.id.text1)
}
