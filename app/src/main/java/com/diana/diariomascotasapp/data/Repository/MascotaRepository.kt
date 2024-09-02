package com.diana.diariomascotasapp.data.repository

import com.diana.diariomascotasapp.data.dao.MascotaDao
import com.diana.diariomascotasapp.data.model.Mascota
import kotlinx.coroutines.flow.Flow

class MascotaRepository(private val mascotaDao: MascotaDao) {

    // Método para obtener una lista de mascotas
    fun getMascotas(): Flow<List<Mascota>> {
        return mascotaDao.getAllMascotas() // Retorna el Flow de la base de datos
    }

    // Método para agregar una nueva mascota
    suspend fun addMascota(mascota: Mascota) {
        mascotaDao.insertMascota(mascota) // Agrega la mascota a la base de datos
    }
}
