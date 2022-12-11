package com.example.futax.di

import android.content.Context
import androidx.room.Room
import com.example.futax.futax_application.data.local.LogsDatabase
import com.example.futax.futax_application.data.local.dao.ComplexLogsDao
import com.example.futax.futax_application.data.local.dao.SimpleLogsDao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideSimpleLogDao(logsDatabase: LogsDatabase): SimpleLogsDao {
        return logsDatabase.simpleLogDao()
    }

    @Provides
    fun provideComplexLogDao(logsDatabase: LogsDatabase): ComplexLogsDao {
       return logsDatabase.complexLogDao()
    }

    @Provides
    @Singleton
    fun provideLogsDatabase(@ApplicationContext context: Context): LogsDatabase {
        return Room.databaseBuilder(
            context,
            LogsDatabase::class.java,
            "logs_database"
        ).build()
    }
}