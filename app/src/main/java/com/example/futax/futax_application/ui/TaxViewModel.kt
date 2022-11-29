package com.example.futax.futax_application.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futax.futax_application.domain.Log
import com.example.futax.futax_application.domain.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaxViewModel @Inject constructor(private val repository: LocalRepository) : ViewModel() {

    val sellingPrice: MutableStateFlow<Int> = MutableStateFlow(0)
    val earning: MutableStateFlow<Int> = MutableStateFlow(0)
    val taxes: MutableStateFlow<Int> = MutableStateFlow(0)
    val total: MutableStateFlow<Int> = MutableStateFlow(0)
    val quantity: MutableStateFlow<Int> = MutableStateFlow(1)


    init {
        getLogs()
    }

    private suspend fun calculateTotal() {
        total.emit(
            sellingPrice.value * quantity.value
        )
    }

    private suspend fun calculateEarning() {
        earning.emit(
            total.value - (total.value * .05).toInt()
        )
    }

    private suspend fun calculateTaxes() {
        taxes.emit(
            total.value - earning.value
        )
    }

    suspend fun calculateBtnOnClick() {
        calculateTotal()
        calculateEarning()
        calculateTaxes()

        insertLog(Log(0, sellingPrice.value, earning.value))
    }

    private fun getLogs(): Flow<List<Log>> = repository.getAllLogs()

    private fun insertLog(log: Log) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertLog(log)
    }

    private fun deleteLog(log: Log) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteLog(log)
    }

    private fun clearLogs() = viewModelScope.launch(Dispatchers.IO) {
        repository.clearLogs()
    }
}