package com.diana.diariomascotasapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.diana.diariomascotasapp.data.dao.MascotaDao
import com.diana.diariomascotasapp.data.model.Mascota

@Database(entities = [Mascota::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mascotaDao(): MascotaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

