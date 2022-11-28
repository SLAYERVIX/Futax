package com.example.futax.futax_application.domain.repository

import com.example.futax.futax_application.domain.Log
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    fun getAllLogs() : Flow<List<Log>>
    suspend fun insertLog(log: Log)
    suspend fun deleteLog(log: Log)
    suspend fun clearLogs()
}