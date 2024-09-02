package com.diana.diariomascotasapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.diana.diariomascotasapp.data.dao.MascotaDao
import com.diana.diariomascotasapp.data.dao.VisitaVeterinariaDao
import com.diana.diariomascotasapp.data.model.Mascota
import com.diana.diariomascotasapp.data.model.VisitaVeterinaria

@Database(entities = [Mascota::class, VisitaVeterinaria::class], version = 1)
abstract class DiarioMascotasDatabase : RoomDatabase() {
    abstract fun mascotaDao(): MascotaDao
    abstract fun visitaVeterinariaDao(): VisitaVeterinariaDao
}