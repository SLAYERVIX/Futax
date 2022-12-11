package com.example.futax.futax_application.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.futax.databinding.ItemSimpleLogBinding
import com.example.futax.futax_application.data.local.models.SimpleLog
import com.example.futax.futax_application.ui.utils.differs.SimpleLogDiffItemCallBack

class SimpleLogAdapter :
    ListAdapter<SimpleLog, SimpleLogAdapter.SimpleLogViewHolder>(SimpleLogDiffItemCallBack()) {
        inner class SimpleLogViewHolder(val binding: ItemSimpleLogBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(item: SimpleLog) {
                binding.simpleLogItem = item
                binding.executePendingBindings()
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleLogViewHolder {
            return SimpleLogViewHolder(
                ItemSimpleLogBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: SimpleLogViewHolder, position: Int) {
            holder.bind(getItem(position))
        }
}