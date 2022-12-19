package com.example.futax.futax_application.ui.simple

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futax.futax_application.data.local.models.SimpleLog
import com.example.futax.futax_application.domain.models.CalculatorItem
import com.example.futax.futax_application.domain.repository.LocalRepository
import com.example.futax.utils.Date
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SimpleViewModel @Inject constructor(private val localRepository: LocalRepository) :
    ViewModel() {

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

    fun resetFields() = viewModelScope.launch{
        sellingPrice.emit(0)
        quantity.emit(1)
        taxes.emit(0)
        total.emit(0)
        _earning.emit(0)
        _list.emit(setupList())
    }

    private suspend inline fun calculateTotal() {
        total.emit(
            sellingPrice.value * quantity.value
        )
    }

    private suspend inline fun calculateTaxes() {
        taxes.emit(
            (total.value * 0.05).toInt()
        )
    }

    private suspend inline fun calculateEarning() {
        _earning.emit(
            total.value - taxes.value
        )
    }

    fun calculateBtnOnClick() = viewModelScope.launch {
        calculateTotal()
        calculateTaxes()
        calculateEarning()

        _list.emit(setupList())

        insertSimpleLog()
    }

    fun getSimpleLogs(): Flow<List<SimpleLog>> = localRepository.getSimpleLogs()

    private fun insertSimpleLog() = viewModelScope.launch {
        val simpleLog = SimpleLog(
            0,
            Date.date,
            Date.time,
            sellingPrice.value,
            quantity.value,
            taxes.value,
            total.value,
            earning.value,
        )
        localRepository.insertSimpleLog(simpleLog)
    }

    fun deleteSimpleLog(simpleLog: SimpleLog) = viewModelScope.launch {
        localRepository.deleteSimpleLog(simpleLog)
    }

    fun clearSimpleLogs() = viewModelScope.launch {
        localRepository.clearSimpleLogs()
    }
}