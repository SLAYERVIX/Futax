package com.example.futax.futax_application.data.repository

import com.example.futax.futax_application.data.local.dao.ComplexLogsDao
import com.example.futax.futax_application.data.local.dao.SimpleLogsDao
import com.example.futax.futax_application.data.local.models.ComplexLog
import com.example.futax.futax_application.data.local.models.SimpleLog
import com.example.futax.futax_application.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val simpleLogsDao: SimpleLogsDao,
    private val complexLogsDao: ComplexLogsDao
) : LocalRepository {
    override fun getSimpleLogs(): Flow<List<SimpleLog>> {
        return simpleLogsDao.getSimpleLogs()
    }

    override suspend fun insertSimpleLog(simpleLog: SimpleLog) {
        simpleLogsDao.insertSimpleLog(simpleLog)
    }

    override suspend fun deleteSimpleLog(simpleLog: SimpleLog) {
        simpleLogsDao.deleteSimpleLog(simpleLog)
    }

    override suspend fun clearSimpleLogs() {
        simpleLogsDao.clearSimpleLogs()
    }

    override fun getComplexLogs(): Flow<List<ComplexLog>> {
        return complexLogsDao.getComplexLogs()
    }

    override suspend fun insertComplexLog(complexLog: ComplexLog) {
        complexLogsDao.insertComplexLog(complexLog)
    }

    override suspend fun deleteComplexLog(complexLog: ComplexLog) {
        complexLogsDao.deleteComplexLog(complexLog)
    }

    override suspend fun clearComplexLogs() {
        complexLogsDao.clearComplexLogs()
    }
}