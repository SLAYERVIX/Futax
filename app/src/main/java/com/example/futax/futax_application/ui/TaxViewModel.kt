package com.example.futax.futax_application.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futax.futax_application.domain.Log
import com.example.futax.futax_application.domain.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaxViewModel @Inject constructor(private val repository: LocalRepository) : ViewModel() {
    val earning: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            postValue(0)
        }
    }

    val sellingPrice: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply {
            postValue(0)
        }
    }

    private val logsList: MutableLiveData<List<Log>> by lazy {
        MutableLiveData<List<Log>>()
    }

    init {
        getLogs()
    }

    fun calculateEarning() {
        val value: Int = sellingPrice.value!!.toInt()
        earning.value = value - (value * .05).toInt()
    }

    private fun getLogs() {
        viewModelScope.launch(Dispatchers.IO) {
            logsList.postValue(repository.getAllLogs())
        }
    }

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