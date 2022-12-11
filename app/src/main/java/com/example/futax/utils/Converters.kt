package com.example.futax.utils

import androidx.databinding.InverseMethod

object Converters {
    @InverseMethod("stringToInt")
    @JvmStatic
    fun priceToString(value: Int): String {
        if (value != 0) {
            return value.toString()
        }
        return ""
    }

    @JvmStatic
    fun stringToInt(value: String): Int {
        if(value.isNotEmpty()) {
            return value.toInt()
        }
        return 0
    }
}
