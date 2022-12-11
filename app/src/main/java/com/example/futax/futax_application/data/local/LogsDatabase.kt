package com.example.futax.futax_application.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.futax.futax_application.data.local.dao.ComplexLogsDao
import com.example.futax.futax_application.data.local.dao.SimpleLogsDao
import com.example.futax.futax_application.data.local.models.ComplexLog
import com.example.futax.futax_application.data.local.models.SimpleLog

@Database(entities = [SimpleLog::class,ComplexLog::class], version = 1, exportSchema = false)
abstract class LogsDatabase : RoomDatabase() {
    abstract fun simpleLogDao() : SimpleLogsDao
    abstract fun complexLogDao() : ComplexLogsDao
}