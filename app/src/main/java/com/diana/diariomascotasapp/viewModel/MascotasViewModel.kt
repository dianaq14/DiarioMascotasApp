package com.diana.diariomascotasapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diana.diariomascotasapp.data.model.Mascota
import com.diana.diariomascotasapp.data.repository.MascotaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.update

sealed class MascotaUiState {
    object Loading : MascotaUiState()
    data class Success(val mascotas: List<Mascota>) : MascotaUiState()
    data class Error(val message: String) : MascotaUiState()
}

class MascotaViewModel(private val repository: MascotaRepository) : ViewModel() {

    // El estado general de la UI para manejar la lista de mascotas
    private val _uiState = MutableStateFlow<MascotaUiState>(MascotaUiState.Loading)
    val uiState: StateFlow<MascotaUiState> = _uiState

    // Almacenamos la lista de mascotas separadamente
    private val _mascotas = MutableStateFlow<List<Mascota>>(emptyList())
    val mascotas: StateFlow<List<Mascota>> = _mascotas

    init {
        fetchMascotas() // Al iniciar el ViewModel, obtenemos las mascotas
    }

    // Función para obtener las mascotas del repositorio
    private fun fetchMascotas() {
        _uiState.value = MascotaUiState.Loading // Indicamos que estamos cargando
        viewModelScope.launch {
            try {
                val listaMascotas = repository.getMascotas()
                _mascotas.value = listaMascotas // Actualizamos la lista de mascotas
                _uiState.value = MascotaUiState.Success(listaMascotas) // Actualizamos el estado de éxito
            } catch (e: Exception) {
                _uiState.value = MascotaUiState.Error("Error al cargar mascotas: ${e.message}") // Error manejado
            }
        }
    }

    // Función para agregar una nueva mascota
    fun addMascota(mascota: Mascota) {
        viewModelScope.launch {
            try {
                repository.addMascota(mascota) // Agregamos la nueva mascota al repositorio
                fetchMascotas() // Refrescamos la lista de mascotas
            } catch (e: Exception) {
                _uiState.value = MascotaUiState.Error("Error al agregar mascota: ${e.message}") // Error manejado
            }
        }
    }
}
