package com.example.futax.futax_application.ui.utils.differs

import androidx.recyclerview.widget.DiffUtil
import com.example.futax.futax_application.data.local.models.SimpleLog

class SimpleLogDiffItemCallBack : DiffUtil.ItemCallback<SimpleLog>() {
    override fun areItemsTheSame(oldItem: SimpleLog, newItem: SimpleLog): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SimpleLog, newItem: SimpleLog): Boolean {
        return oldItem == newItem
    }
}