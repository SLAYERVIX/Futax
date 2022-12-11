package com.example.futax.futax_application.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.futax.databinding.ItemCalculatorResultBinding
import com.example.futax.futax_application.domain.models.CalculatorItem
import com.example.futax.futax_application.ui.utils.differs.CalculatorDiffItemCallBack

class ComplexAdapter :
    ListAdapter<CalculatorItem, ComplexAdapter.ComplexViewHolder>(CalculatorDiffItemCallBack()) {
    inner class ComplexViewHolder(val binding: ItemCalculatorResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CalculatorItem) {
            binding.calculatorItem = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplexViewHolder {
        return ComplexViewHolder(
            ItemCalculatorResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ComplexViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}