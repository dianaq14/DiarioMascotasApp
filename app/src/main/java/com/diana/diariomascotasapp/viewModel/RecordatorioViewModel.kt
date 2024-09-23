package com.diana.diariomascotasapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diana.diariomascotasapp.data.Repository.RecordatorioRepository
import com.diana.diariomascotasapp.data.model.Recordatorio
import kotlinx.coroutines.launch

class RecordatorioViewModel(
    private val repository: RecordatorioRepository
) : ViewModel() {

    private val _recordatorios = MutableLiveData<List<Recordatorio>>()
    val recordatorios: LiveData<List<Recordatorio>> get() = _recordatorios

    // Función para cargar recordatorios desde el repositorio
    fun cargarRecordatorios(mascotaId: String) {
        viewModelScope.launch {
            val data = repository.getRecordatoriosByMascota(mascotaId)  // Corregido
            _recordatorios.postValue(data)
        }
    }

    // Función para eliminar un recordatorio
    fun eliminarRecordatorio(recordatorioId: String, mascotaId: String) {
        viewModelScope.launch {
            repository.deleteRecordatorio(recordatorioId)  // Corregido
            cargarRecordatorios(mascotaId) // Actualizar lista después de eliminar
        }
    }
}
