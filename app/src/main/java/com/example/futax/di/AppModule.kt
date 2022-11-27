package com.example.futax.di

import android.content.Context
import androidx.room.Room
import com.example.futax.futax_application.data.local.LogsDao
import com.example.futax.futax_application.data.local.LogsDatabase
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
    fun provideLogsDao(logsDatabase: LogsDatabase): LogsDao {
        return logsDatabase.logsDao()
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