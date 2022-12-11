package com.example.futax.futax_application.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.futax.futax_application.data.local.models.SimpleLog
import kotlinx.coroutines.flow.Flow

@Dao
interface SimpleLogsDao {
    @Query("SELECT * FROM simple_log_table")
    fun getSimpleLogs(): Flow<List<SimpleLog>>

    @Insert
    suspend fun insertSimpleLog(simpleLog: SimpleLog)

    @Delete
    suspend fun deleteSimpleLog(simpleLog: SimpleLog)

    @Query("DELETE FROM simple_log_table")
    suspend fun clearSimpleLogs()
}