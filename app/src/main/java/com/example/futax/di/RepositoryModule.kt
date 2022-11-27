package com.example.futax.di

import com.example.futax.futax_application.data.local.LogsDao
import com.example.futax.futax_application.data.repository.LocalRepositoryImpl
import com.example.futax.futax_application.domain.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun bindLocalRepository(
        logsDao: LogsDao
    ) : LocalRepository = LocalRepositoryImpl(logsDao)
}