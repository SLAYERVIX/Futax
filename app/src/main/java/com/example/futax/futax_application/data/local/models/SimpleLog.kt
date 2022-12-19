package com.example.futax.futax_application.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "simple_log_table")
data class SimpleLog(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "time")
    val time: String,

    @ColumnInfo(name = "selling_price")
    val sellingPrice: Int,

    @ColumnInfo(name = "quantity")
    val quantity: Int,

    @ColumnInfo(name = "taxes")
    val taxes: Int,

    @ColumnInfo(name = "total")
    val total: Int,

    @ColumnInfo(name = "earning")
    val earning: Int
)

