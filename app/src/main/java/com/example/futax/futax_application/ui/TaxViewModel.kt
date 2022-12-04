package com.example.futax.futax_application.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futax.futax_application.data.local.models.Log
import com.example.futax.futax_application.domain.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaxViewModel @Inject constructor(private val repository: LocalRepository) : ViewModel() {

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