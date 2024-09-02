package com.diana.diariomascotasapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diana.diariomascotasapp.data.repository.MascotaRepository

class MascotaViewModelFactory(private val mascotaRepository: MascotaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MascotaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MascotaViewModel(mascotaRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
