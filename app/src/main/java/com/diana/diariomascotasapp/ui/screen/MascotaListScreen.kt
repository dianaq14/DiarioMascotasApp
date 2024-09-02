package com.diana.diariomascotasapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diana.diariomascotasapp.data.model.Mascota
import com.diana.diariomascotasapp.viewModel.MascotaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MascotaListScreen(mascotaViewModel: MascotaViewModel = viewModel()) {
    val mascotas by mascotaViewModel.mascotas.collectAsState(initial = emptyList())
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Mis Mascotas") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Mascota")
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(mascotas.size) { index ->
                MascotaItem(mascota = mascotas[index])
            }
        }
        if (showAddDialog) {
            AddMascotaDialog(onDismiss = { showAddDialog = false }) { newMascota ->
                mascotaViewModel.addMascota(newMascota)
                showAddDialog = false
            }
        }
    }
}

@Composable
fun MascotaItem(mascota: Mascota) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Navegar a la pantalla de detalles */ },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Nombre: ${mascota.nombre}")
            Text(text = "Especie: ${mascota.especie}")
            Text(text = "Raza: ${mascota.raza}")
            Text(text = "Fecha de Nacimiento: ${mascota.fechaNacimiento}")
        }
    }
}

@Composable
fun AddMascotaDialog(onDismiss: () -> Unit, onAdd: (Mascota) -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var especie by remember { mutableStateOf("") }
    var raza by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Agregar Mascota") },
        text = {
            Column {
                TextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
                TextField(value = especie, onValueChange = { especie = it }, label = { Text("Especie") })
                TextField(value = raza, onValueChange = { raza = it }, label = { Text("Raza") })
                TextField(value = fechaNacimiento, onValueChange = { fechaNacimiento = it }, label = { Text("Fecha de Nacimiento") })
            }
        },
        confirmButton = {
            Button(onClick = {
                onAdd(Mascota(nombre = nombre, especie = especie, raza = raza, fechaNacimiento = fechaNacimiento))
            }) {
                Text("Agregar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MascotaListScreenPreview() {
    MascotaListScreen()
}