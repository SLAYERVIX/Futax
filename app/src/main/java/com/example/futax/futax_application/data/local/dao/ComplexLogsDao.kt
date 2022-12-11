package com.example.futax.futax_application.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.futax.futax_application.data.local.models.ComplexLog
import kotlinx.coroutines.flow.Flow

@Dao
interface ComplexLogsDao {
    @Query("SELECT * FROM complex_log_table")
    fun getComplexLogs(): Flow<List<ComplexLog>>

    @Insert
    suspend fun insertComplexLog(complexLog: ComplexLog)

    @Delete
    suspend fun deleteComplexLog(complexLog: ComplexLog)

    @Query("DELETE FROM complex_log_table")
    suspend fun clearComplexLogs()
}