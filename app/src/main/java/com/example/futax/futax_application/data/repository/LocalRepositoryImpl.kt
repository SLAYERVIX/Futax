package com.example.futax.futax_application.data.repository

import com.example.futax.futax_application.data.local.LogsDao
import com.example.futax.futax_application.data.local.models.SimpleLog
import com.example.futax.futax_application.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val logsDao: LogsDao) : LocalRepository {
    override fun getAllLogs(): Flow<List<SimpleLog>> {
        return logsDao.getAllLogs()
    }

    override suspend fun insertLog(simpleLog: SimpleLog) {
        logsDao.insertLog(simpleLog)
    }

    override suspend fun deleteLog(simpleLog: SimpleLog) {
        logsDao.deleteLog(simpleLog)
    }

    override suspend fun clearLogs() {
        logsDao.clearLogs()
    }
}