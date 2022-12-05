package com.example.futax.futax_application.ui.differs

import androidx.recyclerview.widget.DiffUtil
import com.example.futax.futax_application.domain.models.SimpleItem

class SimpleDiffItemCallBack() : DiffUtil.ItemCallback<SimpleItem>()  {
    override fun areItemsTheSame(oldItem: SimpleItem, newItem: SimpleItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: SimpleItem, newItem: SimpleItem): Boolean {
        return oldItem == newItem
    }
}