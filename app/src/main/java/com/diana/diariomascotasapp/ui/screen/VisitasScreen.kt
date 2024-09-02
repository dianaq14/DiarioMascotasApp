package com.diana.diariomascotasapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diana.diariomascotasapp.data.model.Visita
import com.diana.diariomascotasapp.viewModel.VisitaViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitasScreen(
    visitaViewModel: VisitaViewModel = viewModel(),
    onNavigateToHome: () -> Unit
) {
    val visitas by visitaViewModel.visitas.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var visitaFecha by remember { mutableStateOf("") }
    var visitaDescripcion by remember { mutableStateOf("") }
    var visitaVeterinario by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Visitas Veterinarias") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Visita")
            }
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
                Text("Historial de Visitas", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                visitas.forEach { visita ->
                    Card(modifier = Modifier.padding(vertical = 4.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Fecha: ${visita.fecha}", style = MaterialTheme.typography.bodyLarge)
                            Text("Descripción: ${visita.descripcion}", style = MaterialTheme.typography.bodyMedium)
                            Text("Veterinario: ${visita.veterinario}", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onNavigateToHome) {
                    Text("Volver a Inicio")
                }
            }
        }
    )

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Agregar Visita") },
            text = {
                Column {
                    TextField(value = visitaFecha, onValueChange = { visitaFecha = it }, label = { Text("Fecha") })
                    TextField(value = visitaDescripcion, onValueChange = { visitaDescripcion = it }, label = { Text("Descripción") })
                    TextField(value = visitaVeterinario, onValueChange = { visitaVeterinario = it }, label = { Text("Veterinario") })
                }
            },
            confirmButton = {
                Button(onClick = {
                    val newVisita = Visita(fecha = visitaFecha, descripcion = visitaDescripcion, veterinario = visitaVeterinario)
                    visitaViewModel.addVisita(newVisita)
                    showDialog = false
                }) {
                    Text("Agregar")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VisitasScreenPreview() {
    VisitasScreen(onNavigateToHome = {})
}
