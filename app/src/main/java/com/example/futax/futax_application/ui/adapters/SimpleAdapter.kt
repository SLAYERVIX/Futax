package com.example.futax.futax_application.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.futax.databinding.ItemSimpleBinding
import com.example.futax.futax_application.domain.models.SimpleLog

class SimpleAdapter(val list : List<SimpleLog>) : RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder>() {

    inner class SimpleViewHolder(val binding: ItemSimpleBinding) : RecyclerView.ViewHolder(binding.root)

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
        holder.binding.apply {
            tvSimpleTitle.text = list[position].title
            tvSimpleData.text = list[position].data.toString()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}