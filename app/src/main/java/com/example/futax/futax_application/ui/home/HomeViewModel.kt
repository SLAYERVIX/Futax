package com.example.futax.futax_application.ui.home

import android.util.Log
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
        Log.e("simple",_simpleLogSize.value.toString())
    }

    private fun getSimpleSize() = viewModelScope.launch {
        getSimpleLogs().collect {
            _simpleLogSize.emit(it.size)
            Log.e("simple",simpleLogSize.value.toString())
        }
    }

    private fun getComplexSize() = viewModelScope.launch {
        getComplexLogs().collect {
            _complexLogSize.emit(it.size)
        }
    }

    fun getSimpleLogs(): Flow<List<SimpleLog>> = localRepository.getSimpleLogs()
    fun getComplexLogs() : Flow<List<ComplexLog>> = localRepository.getComplexLogs()
}