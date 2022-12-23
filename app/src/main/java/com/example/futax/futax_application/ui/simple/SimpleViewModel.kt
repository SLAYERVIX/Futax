package com.example.futax.futax_application.ui.simple

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futax.futax_application.data.local.models.SimpleLog
import com.example.futax.futax_application.domain.repository.LocalRepository
import com.example.futax.utils.Date
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SimpleViewModel @Inject constructor(
    private val localRepository: LocalRepository,
) :
    ViewModel() {

    val sellingPrice: MutableStateFlow<Int> = MutableStateFlow(0)

    val quantity: MutableStateFlow<Int> = MutableStateFlow(1)

    private val _taxes: MutableStateFlow<Int> = MutableStateFlow(0)
    val taxes: StateFlow<Int> = _taxes

    private val _total: MutableStateFlow<Int> = MutableStateFlow(0)
    val total: StateFlow<Int> = _total

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

    fun resetFields() = viewModelScope.launch {
        sellingPrice.emit(0)
        quantity.emit(1)
        _taxes.emit(0)
        _total.emit(0)
        _earning.emit(0)
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

    fun calculateBtnOnClick() = viewModelScope.launch {
        calculateTotal()
        calculateTaxes()
        calculateEarning()

        val simpleLog = SimpleLog(
            0,
            Date.date,
            Date.time,
            sellingPrice.value,
            quantity.value,
            _taxes.value,
            _total.value,
            earning.value,
        )

        insertSimpleLog(simpleLog)
    }

    fun getSimpleLogs(): Flow<List<SimpleLog>> = localRepository.getSimpleLogs()

    fun insertSimpleLog(simpleLog: SimpleLog) = viewModelScope.launch {
        localRepository.insertSimpleLog(simpleLog)
    }

    fun deleteSimpleLog(simpleLog: SimpleLog) = viewModelScope.launch {
        localRepository.deleteSimpleLog(simpleLog)
    }

    fun clearSimpleLogs() = viewModelScope.launch {
        localRepository.clearSimpleLogs()
    }
}