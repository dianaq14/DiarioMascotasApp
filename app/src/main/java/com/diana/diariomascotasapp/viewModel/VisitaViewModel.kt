package com.diana.diariomascotasapp.viewModel

import androidx.lifecycle.ViewModel
import com.diana.diariomascotasapp.data.model.Visita
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VisitaViewModel : ViewModel() {

    // Lista de visitas, usando MutableStateFlow para cambios observables.
    private val _visitas = MutableStateFlow<List<Visita>>(emptyList())
    val visitas: StateFlow<List<Visita>> = _visitas

    // Función para agregar una nueva visita
    fun addVisita(visita: Visita) {
        _visitas.value = _visitas.value + visita
    }
}
