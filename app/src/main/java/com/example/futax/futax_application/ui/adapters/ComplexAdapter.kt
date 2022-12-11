package com.example.futax.futax_application.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.futax.databinding.CalculatorItemBinding
import com.example.futax.futax_application.domain.models.CalculatorItem
import com.example.futax.futax_application.ui.utils.differs.CalculatorDiffItemCallBack

class ComplexAdapter :
    ListAdapter<CalculatorItem, ComplexAdapter.ComplexViewHolder>(CalculatorDiffItemCallBack()) {
    inner class ComplexViewHolder(val binding: CalculatorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CalculatorItem) {
            binding.calculatorItem = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplexViewHolder {
        return ComplexViewHolder(
            CalculatorItemBinding.inflate(
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