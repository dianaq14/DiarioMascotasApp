package com.diana.diariomascotasapp.workers

import ReminderWorkerFactory
import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val workManagerConfig = Configuration.Builder()
            .setWorkerFactory(ReminderWorkerFactory(this))
            .build()

        WorkManager.initialize(this, workManagerConfig)
    }
}
