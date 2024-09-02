package com.diana.diariomascotasapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.diana.diariomascotasapp.data.model.Mascota
import kotlinx.coroutines.flow.Flow



@Dao
interface MascotaDao {

    // Método para obtener todas las mascotas
    @Query("SELECT * FROM mascota")
    fun getAllMascotas(): Flow<List<Mascota>> // Retorna un Flow con la lista de mascotas

    // Método para insertar una nueva mascota
    @Insert
    suspend fun insertMascota(mascota: Mascota)
}
