package com.example.futax.futax_application.ui.utils.differs

import androidx.recyclerview.widget.DiffUtil
import com.example.futax.futax_application.data.local.models.ComplexLog

class ComplexLogDiffItemCallBack : DiffUtil.ItemCallback<ComplexLog>() {
    override fun areItemsTheSame(oldItem: ComplexLog, newItem: ComplexLog): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ComplexLog, newItem: ComplexLog): Boolean {
        return oldItem == newItem
    }
}