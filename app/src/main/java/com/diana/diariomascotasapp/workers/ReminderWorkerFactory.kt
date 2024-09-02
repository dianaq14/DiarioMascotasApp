import androidx.work.Worker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import android.content.Context
import com.diana.diariomascotasapp.workers.ReminderWorker

class ReminderWorkerFactory(
    private val appContext: Context
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParams: WorkerParameters
    ): Worker? {
        return if (workerClassName == ReminderWorker::class.java.name) {
            ReminderWorker(appContext, workerParams)
        } else {
            null
        }
    }
}
