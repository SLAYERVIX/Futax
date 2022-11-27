package com.example.futax.futax_application.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.futax.futax_application.domain.Log

interface LocalRepository {
    fun getAllLogs() : List<Log>
    suspend fun insertLog(log: Log)
    suspend fun deleteLog(log: Log)
    suspend fun clearLogs()
}