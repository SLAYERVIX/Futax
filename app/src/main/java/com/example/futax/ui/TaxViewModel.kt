package com.example.futax.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaxViewModel : ViewModel() {
    val earning: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            postValue(0)
        }
    }

    fun calculateEarning(value: Int) {
        earning.value = value - (value * .05).toInt()
    }
}