package com.example.futax.futax_application.ui.complex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futax.futax_application.data.local.models.ComplexLog
import com.example.futax.futax_application.domain.models.CalculatorItem
import com.example.futax.futax_application.domain.repository.LocalRepository
import com.example.futax.utils.Date
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComplexViewModel @Inject constructor(private val localRepository: LocalRepository) :
    ViewModel() {
    val buyPrice: MutableStateFlow<Int> = MutableStateFlow(0)

    val sellingPrice: MutableStateFlow<Int> = MutableStateFlow(0)

    val quantity: MutableStateFlow<Int> = MutableStateFlow(1)

    private val taxes: MutableStateFlow<Int> = MutableStateFlow(0)

    private val total: MutableStateFlow<Int> = MutableStateFlow(0)

    private val profit: MutableStateFlow<Int> = MutableStateFlow(0)

    private val _earning: MutableStateFlow<Int> = MutableStateFlow(0)
    val earning: StateFlow<Int> = _earning

    private var _list: MutableStateFlow<List<CalculatorItem>> = MutableStateFlow(setupList())
    val list: StateFlow<List<CalculatorItem>> = _list

    val logsList: Flow<List<ComplexLog>> = getComplexLogs()


    private fun setupList(): MutableList<CalculatorItem> {
        return mutableListOf(
            CalculatorItem("Total:", total.value),
            CalculatorItem("Taxes:", taxes.value),
            CalculatorItem("Profit:", profit.value)
        )
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

    private suspend inline fun calculateProfit() {
        profit.emit(
            _earning.value - buyPrice.value
        )
    }

    fun calculateBtnOnClick() = viewModelScope.launch {
        calculateTotal()
        calculateTaxes()
        calculateEarning()
        calculateProfit()
        _list.emit(setupList())
        insertComplexLog()
    }

    fun resetFields() = viewModelScope.launch {
        buyPrice.emit(0)
        sellingPrice.emit(0)
        quantity.emit(1)
        taxes.emit(0)
        profit.emit(0)
        total.emit(0)
        _earning.emit(0)
        _list.emit(setupList())
    }

    fun getComplexLogs(): Flow<List<ComplexLog>> = localRepository.getComplexLogs()

    private fun insertComplexLog() = viewModelScope.launch(Dispatchers.IO) {
        val complexLog = ComplexLog(
            0,
            Date.date,
            buyPrice.value,
            sellingPrice.value,
            quantity.value,
            taxes.value,
            total.value,
            earning.value,
            profit.value
        )
        localRepository.insertComplexLog(complexLog)
    }

    fun deleteComplexLog(complexLog: ComplexLog) = viewModelScope.launch(Dispatchers.IO) {
        localRepository.deleteComplexLog(complexLog)
    }

    fun clearComplexLogs() = viewModelScope.launch(Dispatchers.IO) {
        localRepository.clearComplexLogs()
    }
}