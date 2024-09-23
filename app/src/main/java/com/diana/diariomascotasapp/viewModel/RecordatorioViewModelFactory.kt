package com.diana.diariomascotasapp.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diana.diariomascotasapp.data.Repository.RecordatorioRepository

class RecordatorioViewModelFactory(
    private val repository: RecordatorioRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecordatorioViewModel::class.java)) {
            return RecordatorioViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
