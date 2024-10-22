package com.diana.diariomascotasapp.data.model

data class Visita(
    val fecha: String,
    val motivo: String,
    val veterinario: String,
    val mascotaId: Int,
    val detalles: String,
    val notas: String
)



