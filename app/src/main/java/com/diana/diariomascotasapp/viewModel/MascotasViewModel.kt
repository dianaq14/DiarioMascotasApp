package com.diana.diariomascotasapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diana.diariomascotasapp.data.model.Mascota
import com.diana.diariomascotasapp.data.repository.MascotaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MascotaViewModel(private val mascotaRepository: MascotaRepository) : ViewModel() {

    // Flow que contiene la lista de mascotas
    val mascotas: Flow<List<Mascota>> = mascotaRepository.getMascotas()

    // Método para agregar una nueva mascota
    fun addMascota(mascota: Mascota) {
        viewModelScope.launch {
            mascotaRepository.addMascota(mascota)
        }
    }
}
