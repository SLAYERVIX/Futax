package com.example.futax.futax_application.ui.home

import androidx.lifecycle.ViewModel
import com.example.futax.futax_application.data.local.models.ComplexLog
import com.example.futax.futax_application.data.local.models.SimpleLog
import com.example.futax.futax_application.domain.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val localRepository: LocalRepository) : ViewModel() {
    fun getSimpleLogs(): Flow<List<SimpleLog>> = localRepository.getSimpleLogs()
    fun getComplexLogs() : Flow<List<ComplexLog>> = localRepository.getComplexLogs()
}