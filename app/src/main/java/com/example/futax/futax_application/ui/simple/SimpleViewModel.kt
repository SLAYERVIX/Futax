package com.example.futax.futax_application.ui.simple

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futax.futax_application.domain.models.CalculatorItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SimpleViewModel : ViewModel() {

    val sellingPrice: MutableStateFlow<Int> = MutableStateFlow(0)

    val quantity: MutableStateFlow<Int> = MutableStateFlow(1)

    private val taxes: MutableStateFlow<Int> = MutableStateFlow(0)

    private val total: MutableStateFlow<Int> = MutableStateFlow(0)

    private val _earning: MutableStateFlow<Int> = MutableStateFlow(0)
    val earning: StateFlow<Int> = _earning

    private var _list: MutableStateFlow<List<CalculatorItem>> = MutableStateFlow(setupList())
    val list: StateFlow<List<CalculatorItem>> = _list

    private fun setupList(): MutableList<CalculatorItem> {
        return mutableListOf(
            CalculatorItem("Total", total.value),
            CalculatorItem("Taxes", taxes.value)
        )
    }

    private suspend fun calculateTotal() {
        total.emit(
            sellingPrice.value * quantity.value
        )
    }

    private suspend fun calculateTaxes() {
        taxes.emit(
            (total.value * 0.05).toInt()
        )
    }

    private suspend fun calculateEarning() {
        _earning.emit(
            total.value - taxes.value
        )
    }

    fun calculateBtnOnClick() = viewModelScope.launch {
        calculateTotal()
        calculateTaxes()
        calculateEarning()

        _list.emit(setupList())
    }
}