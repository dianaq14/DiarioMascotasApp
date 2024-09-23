package com.diana.diariomascotasapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.diana.diariomascotasapp.data.model.Mascota
import kotlinx.coroutines.flow.Flow



@Dao
interface MascotaDao {
    @Query("SELECT * FROM mascota")
    fun getAllMascotas(): Flow<List<Mascota>>

    @Insert
    suspend fun insertMascota(mascota: Mascota)
}