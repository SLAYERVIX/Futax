package com.example.futax.futax_application.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.futax.databinding.ItemSimpleBinding
import com.example.futax.futax_application.domain.models.SimpleItem
import com.example.futax.futax_application.ui.differs.SimpleDiffItemCallBack

class SimpleAdapter() :
    ListAdapter<SimpleItem, SimpleAdapter.SimpleViewHolder>(SimpleDiffItemCallBack()) {

    inner class SimpleViewHolder(val binding: ItemSimpleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SimpleItem) {
            binding.simpleItem = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        return SimpleViewHolder(
            binding = ItemSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}