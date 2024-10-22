package com.diana.diariomascotasapp.data.repository

import com.diana.diariomascotasapp.data.model.Mascota
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

open class MascotaRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val mascotasCollection = firestore.collection("mascotas")

    open suspend fun addMascota(mascota: Mascota) {
        try {
            mascotasCollection.add(mascota).await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    open suspend fun getMascotas(): List<Mascota> {
        return try {
            val snapshot = mascotasCollection.get().await()
            snapshot.documents.mapNotNull { document ->
                document.toObject(Mascota::class.java)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    open suspend fun updateMascota(id: String, mascota: Mascota) {
        try {
            mascotasCollection.document(id).set(mascota).await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    open suspend fun deleteMascota(id: String) {
        try {
            mascotasCollection.document(id).delete().await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
