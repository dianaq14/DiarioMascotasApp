package com.diana.diariomascotasapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diana.diariomascotasapp.data.model.Mascota
import com.diana.diariomascotasapp.ui.theme.PetPlannerTheme
import com.diana.diariomascotasapp.viewModel.MascotaViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    mascotaViewModel: MascotaViewModel = viewModel(),
    onNavigateToVisitas: () -> Unit
) {
    val mascotas by mascotaViewModel.mascotas.collectAsState(initial = emptyList())
    var showDialog by remember { mutableStateOf(false) }
    var mascotaNombre by remember { mutableStateOf("") }
    var mascotaEspecie by remember { mutableStateOf("") }
    var mascotaRaza by remember { mutableStateOf("") }
    var mascotaFechaNacimiento by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Diario de Mascotas") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Mascota")
            }
        },
        content = { innerPadding ->
            Column(modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)) {
                Text("Lista de Mascotas", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                mascotas.forEach { mascota ->
                    Card(modifier = Modifier
                        .padding(vertical = 4.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(mascota.nombre, style = MaterialTheme.typography.bodyLarge)
                            Text(
                                "${mascota.especie} - ${mascota.raza}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onNavigateToVisitas) {
                    Text("Ver Visitas Veterinarias")
                }
            }
        }
    )

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Agregar Mascota") },
            text = {
                Column {
                    TextField(
                        value = mascotaNombre,
                        onValueChange = { mascotaNombre = it },
                        label = { Text("Nombre") })
                    TextField(
                        value = mascotaEspecie,
                        onValueChange = { mascotaEspecie = it },
                        label = { Text("Especie") })
                    TextField(
                        value = mascotaRaza,
                        onValueChange = { mascotaRaza = it },
                        label = { Text("Raza") })
                    TextField(
                        value = mascotaFechaNacimiento,
                        onValueChange = { mascotaFechaNacimiento = it },
                        label = { Text("Fecha de Nacimiento") })
                }
            },
            confirmButton = {
                Button(onClick = {
                    val newMascota = Mascota(
                        nombre = mascotaNombre,
                        especie = mascotaEspecie,
                        raza = mascotaRaza,
                        fechaNacimiento = mascotaFechaNacimiento
                    )
                    mascotaViewModel.addMascota(newMascota)
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
fun HomeScreenPreview() {
    PetPlannerTheme(darkTheme = true) {
        HomeScreen(onNavigateToVisitas = {})
    }
}
