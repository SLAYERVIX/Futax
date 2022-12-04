package com.example.futax.futax_application.domain.models

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SimpleCalc {
    private val _sellingPrice: MutableStateFlow<Int> = MutableStateFlow(0)
    val sellingPrice: StateFlow<Int> = _sellingPrice

    private val _quantity: MutableStateFlow<Int> = MutableStateFlow(1)
    val quantity: StateFlow<Int> = _quantity

    private val _taxes: MutableStateFlow<Int> = MutableStateFlow(0)
    val taxes: StateFlow<Int> = _taxes

    private val _earning: MutableStateFlow<Int> = MutableStateFlow(0)
    val earning: StateFlow<Int> = _earning

    private val _total: MutableStateFlow<Int> = MutableStateFlow(0)
    val total: StateFlow<Int> = _total

    suspend fun setSellingPrice(sellingPrice: Int) {
        _sellingPrice.emit(
            sellingPrice
        )
    }

    suspend fun setQuantity(quantity: Int) {
        _quantity.emit(
            quantity
        )
    }

    private suspend fun calculateTotal() {
        _total.emit(sellingPrice.value * quantity.value)
    }

    private suspend fun calculateEarning() {
        _earning.emit(
            total.value - (total.value * 0.05).toInt()
        )
    }

    private suspend fun calculateTaxes() {
        _taxes.emit(
            total.value - earning.value
        )
    }

    suspend fun calculate() {
        calculateTotal()
        calculateEarning()
        calculateTaxes()
    }
}