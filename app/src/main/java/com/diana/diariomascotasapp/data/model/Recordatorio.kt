package com.diana.diariomascotasapp.data.model

data class Recordatorio(
    val id: String = "",
    val fecha: String = "",
    val motivo: String = "",
    val descripcion: String = "",
    val mascotaId: String = "" // Relaciona el recordatorio con una mascota específica
)