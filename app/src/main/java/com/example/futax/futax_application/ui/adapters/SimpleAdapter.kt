package com.example.futax.futax_application.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.futax.databinding.ItemCalculatorResultBinding
import com.example.futax.futax_application.domain.models.CalculatorItem
import com.example.futax.futax_application.ui.utils.differs.CalculatorDiffItemCallBack

class SimpleAdapter() :
    ListAdapter<CalculatorItem, SimpleAdapter.SimpleViewHolder>(CalculatorDiffItemCallBack()) {

    inner class SimpleViewHolder(val binding: ItemCalculatorResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CalculatorItem) {
            binding.calculatorItem = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        return SimpleViewHolder(
            binding = ItemCalculatorResultBinding.inflate(
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