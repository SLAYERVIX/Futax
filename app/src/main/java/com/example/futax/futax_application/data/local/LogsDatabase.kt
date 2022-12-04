package com.example.futax.futax_application.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.futax.futax_application.data.local.models.Log

@Database(entities = [Log::class], version = 1, exportSchema = false)
abstract class LogsDatabase : RoomDatabase() {
    abstract fun logsDao() : LogsDao
}