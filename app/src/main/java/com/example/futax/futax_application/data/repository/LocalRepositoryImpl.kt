package com.example.futax.futax_application.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.futax.futax_application.data.local.LogsDao
import com.example.futax.futax_application.domain.Log
import com.example.futax.futax_application.domain.repository.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val logsDao: LogsDao) : LocalRepository {
    override fun getAllLogs(): List<Log> {
        return logsDao.getAllLogs()
    }

    override suspend fun insertLog(log: Log) {
        logsDao.insertLog(log)
    }

    override suspend fun deleteLog(log: Log) {
        logsDao.deleteLog(log)
    }

    override suspend fun clearLogs() {
        logsDao.clearLogs()
    }
}