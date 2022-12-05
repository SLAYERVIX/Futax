package com.example.futax.futax_application.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.futax.futax_application.data.local.models.SimpleLog

@Database(entities = [SimpleLog::class], version = 2, exportSchema = false)
abstract class LogsDatabase : RoomDatabase() {
    abstract fun logsDao() : LogsDao
}