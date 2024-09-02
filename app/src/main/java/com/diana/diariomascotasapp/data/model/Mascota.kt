package com.diana.diariomascotasapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mascota")
data class Mascota(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val especie: String,
    val raza: String,
    val fechaNacimiento: String
)