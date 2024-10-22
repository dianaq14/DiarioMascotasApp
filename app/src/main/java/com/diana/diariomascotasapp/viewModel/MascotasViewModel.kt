package com.diana.diariomascotasapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diana.diariomascotasapp.data.model.Mascota
import com.diana.diariomascotasapp.data.repository.MascotaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class MascotaUiState {
    object Loading : MascotaUiState()
    data class Success(val mascotas: List<Mascota>) : MascotaUiState()
    data class Error(val message: String) : MascotaUiState()
}

class MascotaViewModel(private val repository: MascotaRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<MascotaUiState>(MascotaUiState.Loading)
    val uiState: StateFlow<MascotaUiState> = _uiState

    private val _mascotas = MutableStateFlow<List<Mascota>>(emptyList())
    val mascotas: StateFlow<List<Mascota>> = _mascotas

    init {
        fetchMascotas()
    }

    private fun fetchMascotas() {
        _uiState.value = MascotaUiState.Loading
        viewModelScope.launch {
            try {
                val listaMascotas = repository.getMascotas()
                _mascotas.value = listaMascotas
                _uiState.value = MascotaUiState.Success(listaMascotas)
            } catch (e: Exception) {
                _uiState.value = MascotaUiState.Error("Error al cargar mascotas: ${e.message}")
            }
        }
    }

    fun addMascota(mascota: Mascota) {
        viewModelScope.launch {
            try {
                repository.addMascota(mascota)
                fetchMascotas() // Actualiza la lista después de agregar
            } catch (e: Exception) {
                _uiState.value = MascotaUiState.Error("Error al agregar mascota: ${e.message}")
            }
        }
    }
}
