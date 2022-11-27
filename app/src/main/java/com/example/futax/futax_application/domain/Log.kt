package com.example.futax.futax_application.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "logs_table")
data class Log(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "selling_price")
    val sellingPrice: Int,

    @ColumnInfo(name = "earning")
    val earning: Int
)

