package com.example.futax.futax_application.ui.complex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futax.futax_application.data.local.models.ComplexLog
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

    private val _taxes: MutableStateFlow<Int> = MutableStateFlow(0)
    val taxes: StateFlow<Int> = _taxes

    private val _total: MutableStateFlow<Int> = MutableStateFlow(0)
    val total: StateFlow<Int> = _total

    private val _profit: MutableStateFlow<Int> = MutableStateFlow(0)
    val profit: StateFlow<Int> = _profit

    private val _earning: MutableStateFlow<Int> = MutableStateFlow(0)
    val earning: StateFlow<Int> = _earning

    private val _isDetailsVisible: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isDetailsVisible: StateFlow<Boolean> = _isDetailsVisible

    private val _isLogsEmpty: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLogsEmpty: StateFlow<Boolean> = _isLogsEmpty

    fun setVisibility() = viewModelScope.launch {
        _isDetailsVisible.emit(_isDetailsVisible.value.not())
    }

    suspend fun isLogSetter(state : Boolean) {
        _isLogsEmpty.emit(state)
    }

    private suspend inline fun calculateTotal() {
        _total.emit(
            sellingPrice.value * quantity.value
        )
    }

    private suspend inline fun calculateTaxes() {
        _taxes.emit(
            (_total.value * 0.05).toInt()
        )
    }

    private suspend inline fun calculateEarning() {
        _earning.emit(
            _total.value - _taxes.value
        )
    }

    private suspend inline fun calculateProfit() {
        _profit.emit(
            _earning.value - buyPrice.value
        )
    }

    fun calculateBtnOnClick() = viewModelScope.launch {
        calculateTotal()
        calculateTaxes()
        calculateEarning()
        calculateProfit()

        insertComplexLog(
            ComplexLog(
                0,
                Date.date,
                Date.time,
                buyPrice.value,
                sellingPrice.value,
                quantity.value,
                _taxes.value,
                _total.value,
                earning.value,
                _profit.value
            )
        )
    }

    fun resetFields() = viewModelScope.launch {
        buyPrice.emit(0)
        sellingPrice.emit(0)
        quantity.emit(1)
        _taxes.emit(0)
        _profit.emit(0)
        _total.emit(0)
        _earning.emit(0)
    }

    fun getComplexLogs(): Flow<List<ComplexLog>> = localRepository.getComplexLogs()

    fun insertComplexLog(complexLog: ComplexLog) = viewModelScope.launch(Dispatchers.IO) {
        localRepository.insertComplexLog(complexLog)
    }

    fun deleteComplexLog(complexLog: ComplexLog) = viewModelScope.launch(Dispatchers.IO) {
        localRepository.deleteComplexLog(complexLog)
    }

    fun clearComplexLogs() = viewModelScope.launch(Dispatchers.IO) {
        localRepository.clearComplexLogs()
    }
}