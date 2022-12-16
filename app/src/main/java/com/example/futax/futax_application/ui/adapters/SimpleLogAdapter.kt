package com.example.futax.futax_application.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.futax.R
import com.example.futax.databinding.ItemSimpleLogGridBinding
import com.example.futax.databinding.ItemSimpleLogListBinding
import com.example.futax.futax_application.data.local.models.SimpleLog
import com.example.futax.futax_application.ui.utils.differs.SimpleLogDiffItemCallBack

class SimpleLogAdapter :
    ListAdapter<SimpleLog, SimpleLogAdapter.SimpleLogViewHolders>(SimpleLogDiffItemCallBack()) {

    var isGrid = false

    sealed class SimpleLogViewHolders(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        class SimpleLogListViewHolder(private val binding: ItemSimpleLogListBinding) :
            SimpleLogViewHolders(binding) {
            fun bind(item: SimpleLog) {
                binding.simpleLogItem = item
                binding.executePendingBindings()
            }
        }

        class SimpleLogGridViewHolder(val binding: ItemSimpleLogGridBinding) :
            SimpleLogViewHolders(binding) {
            fun bind(item: SimpleLog) {
                binding.simpleLogItem = item
                binding.executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleLogViewHolders {
        return when (isGrid) {
            false -> SimpleLogViewHolders.SimpleLogListViewHolder(
                ItemSimpleLogListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

            true -> SimpleLogViewHolders.SimpleLogGridViewHolder(
                ItemSimpleLogGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: SimpleLogViewHolders, position: Int) {
        when (holder) {
            is SimpleLogViewHolders.SimpleLogListViewHolder -> holder.bind(getItem(position))
            is SimpleLogViewHolders.SimpleLogGridViewHolder -> holder.bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (isGrid) {
            false -> R.layout.item_simple_log_list
            true -> R.layout.item_simple_log_grid
        }
    }
}