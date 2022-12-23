package com.example.futax.futax_application.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futax.futax_application.data.local.models.ComplexLog
import com.example.futax.futax_application.data.local.models.SimpleLog
import com.example.futax.futax_application.domain.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val localRepository: LocalRepository) : ViewModel() {
    private val _simpleLogSize : MutableStateFlow<Int> = MutableStateFlow(0)
    val simpleLogSize : StateFlow<Int> = _simpleLogSize

    private val _complexLogSize : MutableStateFlow<Int> = MutableStateFlow(0)
    val complexLogSize : StateFlow<Int> = _complexLogSize

    init {
        getSimpleSize()
        getComplexSize()
    }

    private fun getSimpleSize() = viewModelScope.launch {
        getSimpleLogs().collect {
            _simpleLogSize.emit(it.size)
        }
    }

    private fun getComplexSize() = viewModelScope.launch {
        getComplexLogs().collect {
            _complexLogSize.emit(it.size)
        }
    }

    private fun getSimpleLogs(): Flow<List<SimpleLog>> = localRepository.getSimpleLogs()
    private fun getComplexLogs() : Flow<List<ComplexLog>> = localRepository.getComplexLogs()
}