package com.example.futax.futax_application.domain.repository

import com.example.futax.futax_application.data.local.models.SimpleLog
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    fun getAllLogs() : Flow<List<SimpleLog>>
    suspend fun insertLog(simpleLog: SimpleLog)
    suspend fun deleteLog(simpleLog: SimpleLog)
    suspend fun clearLogs()
}