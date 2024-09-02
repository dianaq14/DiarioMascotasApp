package com.diana.diariomascotasapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "visitas_veterinarias")
data class VisitaVeterinaria(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mascotaId: Int,
    val fecha: String,
    val motivo: String,
    val notas: String
)