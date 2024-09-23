package com.diana.diariomascotasapp.data.Repository

import com.diana.diariomascotasapp.data.model.Recordatorio
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class FirebaseRepository {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference.child("recordatorios")

    // Obtener todos los recordatorios para una mascota específica
    suspend fun obtenerRecordatorios(mascotaId: String): List<Recordatorio> {
        val snapshot = database.orderByChild("mascotaId").equalTo(mascotaId).get().await()
        return snapshot.children.mapNotNull { it.getValue(Recordatorio::class.java)?.copy(id = it.key ?: "") }
    }

    // Eliminar un recordatorio
    suspend fun eliminarRecordatorio(recordatorioId: String) {
        database.child(recordatorioId).removeValue().await()
    }
}