package com.diana.diariomascotasapp.workers

import android.app.Application
import androidx.work.*
import com.diana.diariomascotasapp.workers.ReminderWorker
import java.util.concurrent.TimeUnit

class DiarioMascotasApp : Application() {

    override fun onCreate() {
        super.onCreate()
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<ReminderWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "reminder_work",
            ExistingPeriodicWorkPolicy.REPLACE,
            workRequest
        )
    }
}