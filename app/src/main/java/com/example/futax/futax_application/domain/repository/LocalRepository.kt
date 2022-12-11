package com.example.futax.futax_application.domain.repository

import com.example.futax.futax_application.data.local.models.ComplexLog
import com.example.futax.futax_application.data.local.models.SimpleLog
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    fun getSimpleLogs() : Flow<List<SimpleLog>>
    suspend fun insertSimpleLog(simpleLog: SimpleLog)
    suspend fun deleteSimpleLog(simpleLog: SimpleLog)
    suspend fun clearSimpleLogs()

    fun getComplexLogs() : Flow<List<ComplexLog>>
    suspend fun insertComplexLog(complexLog: ComplexLog)
    suspend fun deleteComplexLog(complexLog: ComplexLog)
    suspend fun clearComplexLogs()
}