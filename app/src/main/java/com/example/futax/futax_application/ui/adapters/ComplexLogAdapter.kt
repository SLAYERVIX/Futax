package com.example.futax.futax_application.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.futax.R
import com.example.futax.databinding.ItemComplexLogGridBinding
import com.example.futax.databinding.ItemComplexLogListBinding
import com.example.futax.futax_application.data.local.models.ComplexLog
import com.example.futax.futax_application.ui.utils.differs.ComplexLogDiffItemCallBack

class ComplexLogAdapter :
    ListAdapter<ComplexLog, ComplexLogAdapter.ComplexLogsViewHolders>(ComplexLogDiffItemCallBack()) {

    var isGrid = false

    sealed class ComplexLogsViewHolders(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        class ComplexLogListViewHolder(private val binding: ItemComplexLogListBinding) :
            ComplexLogsViewHolders(binding) {
            fun bind(item: ComplexLog) {
                binding.complexLogItem = item
                binding.executePendingBindings()
            }
        }

        class ComplexLogGridViewHolder(private val binding: ItemComplexLogGridBinding) :
            ComplexLogsViewHolders(binding) {
            fun bind(item: ComplexLog) {
                binding.complexLogItem = item
                binding.executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplexLogsViewHolders {
        return when (isGrid) {
            false -> ComplexLogsViewHolders.ComplexLogListViewHolder(
                ItemComplexLogListBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            true -> ComplexLogsViewHolders.ComplexLogGridViewHolder(
                ItemComplexLogGridBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: ComplexLogsViewHolders, position: Int) {
        when (holder) {
            is ComplexLogsViewHolders.ComplexLogListViewHolder -> holder.bind(getItem(position))
            is ComplexLogsViewHolders.ComplexLogGridViewHolder -> holder.bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (isGrid) {
            false -> R.layout.item_complex_log_list
            true -> R.layout.item_complex_log_grid
        }
    }
}