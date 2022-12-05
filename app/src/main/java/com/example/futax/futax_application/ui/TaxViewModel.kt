package com.example.futax.futax_application.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futax.futax_application.data.local.models.SimpleLog
import com.example.futax.futax_application.domain.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaxViewModel @Inject constructor(private val repository: LocalRepository) : ViewModel() {

    fun getLogs(): Flow<List<SimpleLog>> = repository.getAllLogs()

    fun insertLog(simpleLog: SimpleLog) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertLog(simpleLog)
    }

    fun deleteLog(simpleLog: SimpleLog) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteLog(simpleLog)
    }

    fun clearLogs() = viewModelScope.launch(Dispatchers.IO) {
        repository.clearLogs()
    }
}