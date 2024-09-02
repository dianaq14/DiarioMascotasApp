package com.diana.diariomascotasapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.diana.diariomascotasapp.data.model.VisitaVeterinaria
import kotlinx.coroutines.flow.Flow

@Dao
interface VisitaVeterinariaDao {
    @Insert
    suspend fun insert(visita: VisitaVeterinaria)

    @Query("SELECT * FROM visitas_veterinarias WHERE mascotaId = :mascotaId")
    fun getVisitasByMascota(mascotaId: Int): Flow<List<VisitaVeterinaria>>
}