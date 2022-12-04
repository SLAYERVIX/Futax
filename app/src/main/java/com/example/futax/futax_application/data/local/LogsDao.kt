package com.example.futax.futax_application.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.futax.futax_application.data.local.models.Log
import kotlinx.coroutines.flow.Flow

@Dao
interface LogsDao {
    @Query("SELECT * FROM logs_table")
    fun getAllLogs(): Flow<List<Log>>

    @Insert
    suspend fun insertLog(log: Log)

    @Delete
    suspend fun deleteLog(log: Log)

    @Query("DELETE FROM logs_table")
    suspend fun clearLogs()
}