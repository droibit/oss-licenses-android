package com.github.droibit.oss_licenses.ui.wearable.internal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.droibit.oss_licenses.parser.OssLicense
import com.github.droibit.oss_licenses.ui.wearable.R.layout

internal class OssLicenseListAdapter(
  context: Context,
  private val onItemClickListener: (OssLicense) -> Unit,
) : ListAdapter<OssLicense, ViewHolder>(DIFF_CALLBACK) {
  private val inflater: LayoutInflater = LayoutInflater.from(context)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
      itemView = inflater.inflate(
        layout.list_item_oss_license,
        parent,
        false,
      ),
    ).also { vh ->
      vh.itemView.setOnClickListener {
        onItemClickListener.invoke(getItem(vh.bindingAdapterPosition))
      }
    }
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.text.text = getItem(position).libraryName
  }

  companion object {
    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<OssLicense>() {
      override fun areItemsTheSame(oldItem: OssLicense, newItem: OssLicense): Boolean {
        return oldItem.libraryName == newItem.libraryName
      }

      override fun areContentsTheSame(oldItem: OssLicense, newItem: OssLicense): Boolean {
        return oldItem == newItem
      }
    }
  }
}

internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val text: TextView = itemView.findViewById(android.R.id.text1)
}
