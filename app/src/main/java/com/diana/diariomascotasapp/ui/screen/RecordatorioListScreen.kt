@file:OptIn(ExperimentalMaterial3Api::class)

package com.diana.diariomascotasapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diana.diariomascotasapp.data.model.Recordatorio
import com.diana.diariomascotasapp.viewModel.RecordatorioViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RememberReturnType")
@Composable
fun RecordatorioListScreen(
    mascotaId: String,
    viewModel: RecordatorioViewModel = viewModel(),
    onEliminarClick: (String) -> Unit
) {
    // Cargar recordatorios al iniciar la pantalla
    remember { viewModel.cargarRecordatorios(mascotaId) }

    // Observar cambios en los recordatorios usando observeAsState para LiveData
    val recordatorios = viewModel.recordatorios.observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recordatorios de Mascota") }
            )
        }
    ) {
        if (recordatorios.value.isEmpty()) {
            Text("No hay recordatorios", Modifier.padding(16.dp))
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(recordatorios.value.size) { index ->
                    val recordatorio = recordatorios.value[index]
                    RecordatorioItem(recordatorio, onEliminarClick)
                }
            }
        }
    }
}

@Composable
fun RecordatorioItem(recordatorio: Recordatorio, onEliminarClick: (String) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = "Motivo: ${recordatorio.motivo}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Fecha: ${recordatorio.fecha}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Descripción: ${recordatorio.descripcion}", style = MaterialTheme.typography.bodySmall)

            // Botón para eliminar
            TextButton(
                onClick = { onEliminarClick(recordatorio.id) }
            ) {
                Text("Eliminar", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecordatorioListPreview() {
    val dummyRecordatorios = listOf(
        Recordatorio(id = "1", fecha = "2024-09-15", motivo = "Vacunación", descripcion = "Vacuna contra la rabia"),
        Recordatorio(id = "2", fecha = "2024-10-10", motivo = "Chequeo General", descripcion = "Control general de salud")
    )

    Column {
        dummyRecordatorios.forEach { recordatorio ->
            RecordatorioItem(recordatorio = recordatorio, onEliminarClick = {})
        }
    }
}
