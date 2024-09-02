package com.diana.diariomascotasapp.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.diana.diariomascotasapp.data.dao.MascotaDao
import com.diana.diariomascotasapp.data.model.Mascota

@Database(entities = [Mascota::class], version = 1)
abstract class MascotaDatabase : RoomDatabase() {

    abstract fun mascotaDao(): MascotaDao

    companion object {
        @Volatile
        private var INSTANCE: MascotaDatabase? = null

        fun getDatabase(context: Context): MascotaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MascotaDatabase::class.java,
                    "mascota_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
