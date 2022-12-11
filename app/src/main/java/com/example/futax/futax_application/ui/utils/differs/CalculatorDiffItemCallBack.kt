package com.example.futax.futax_application.ui.utils.differs

import androidx.recyclerview.widget.DiffUtil
import com.example.futax.futax_application.domain.models.CalculatorItem

class CalculatorDiffItemCallBack() : DiffUtil.ItemCallback<CalculatorItem>()  {
    override fun areItemsTheSame(oldItem: CalculatorItem, newItem: CalculatorItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: CalculatorItem, newItem: CalculatorItem): Boolean {
        return oldItem == newItem
    }
}