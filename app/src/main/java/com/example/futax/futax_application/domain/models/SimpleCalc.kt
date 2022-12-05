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

    private var _list: MutableStateFlow<List<SimpleItem>> = MutableStateFlow(setupList())
    val list: StateFlow<List<SimpleItem>> = _list

    private fun setupList(): MutableList<SimpleItem> {
        val simpleItem1 = SimpleItem("Selling Price", sellingPrice.value)
        val simpleItem2 = SimpleItem("Quantity", quantity.value)
        val simpleItem3 = SimpleItem("Total", total.value)
        val simpleItem4 = SimpleItem("Taxes", taxes.value)
        return mutableListOf(simpleItem1, simpleItem2, simpleItem3, simpleItem4)
    }

    suspend fun setSellingPrice(sellingPrice: Int) {
        _sellingPrice.emit(
            sellingPrice
        )
        _list.value[0].data = this.sellingPrice.value
    }

    suspend fun setQuantity(quantity: Int) {
        _quantity.emit(
            quantity
        )
    }

    private suspend fun calculateTotal() {
        _total.emit(
            _sellingPrice.value * _quantity.value
        )
    }

    private suspend fun calculateEarning() {
        _earning.emit(
            _total.value - (total.value * 0.05).toInt()
        )
    }

    private suspend fun calculateTaxes() {
        _taxes.emit(
            _total.value - _earning.value
        )
    }

    suspend fun calculate() {
        calculateTotal()
        calculateEarning()
        calculateTaxes()
    }
}