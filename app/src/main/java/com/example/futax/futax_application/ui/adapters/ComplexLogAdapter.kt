package com.example.futax.futax_application.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.futax.databinding.ItemComplexLogBinding
import com.example.futax.futax_application.data.local.models.ComplexLog
import com.example.futax.futax_application.ui.utils.differs.ComplexLogDiffItemCallBack

class ComplexLogAdapter :
    ListAdapter<ComplexLog, ComplexLogAdapter.ComplexLogViewHolder>(ComplexLogDiffItemCallBack()) {
    inner class ComplexLogViewHolder(val binding: ItemComplexLogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ComplexLog) {
            binding.complexLogItem = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplexLogViewHolder {
        return ComplexLogViewHolder(
            ItemComplexLogBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ComplexLogViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}