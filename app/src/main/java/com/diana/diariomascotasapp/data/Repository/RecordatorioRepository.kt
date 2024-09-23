package com.diana.diariomascotasapp.data.Repository


import com.diana.diariomascotasapp.data.model.Recordatorio
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class RecordatorioRepository {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference.child("recordatorios")

    // Obtener todos los recordatorios para una mascota específica
    suspend fun getRecordatoriosByMascota(mascotaId: String): List<Recordatorio> {
        val snapshot = database.orderByChild("mascotaId").equalTo(mascotaId).get().await()
        return snapshot.children.mapNotNull { it.getValue(Recordatorio::class.java)?.copy(id = it.key ?: "") }
    }

    // Agregar un nuevo recordatorio
    suspend fun addRecordatorio(recordatorio: Recordatorio) {
        val id = database.push().key ?: return
        database.child(id).setValue(recordatorio.copy(id = id)).await()
    }

    // Actualizar un recordatorio existente
    suspend fun updateRecordatorio(recordatorio: Recordatorio) {
        database.child(recordatorio.id).setValue(recordatorio).await()
    }

    // Eliminar un recordatorio
    suspend fun deleteRecordatorio(recordatorioId: String) {
        database.child(recordatorioId).removeValue().await()
    }
}