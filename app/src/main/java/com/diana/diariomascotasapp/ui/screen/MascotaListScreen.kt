package com.diana.diariomascotasapp.ui.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diana.diariomascotasapp.data.model.Mascota
import com.diana.diariomascotasapp.viewModel.MascotaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MascotaListScreen(mascotaViewModel: MascotaViewModel = viewModel(), onMascotaClick: (Mascota) -> Unit) {
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
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(mascotas.size) { index ->
                MascotaItem(mascota = mascotas[index], onClick = onMascotaClick)
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
fun MascotaItem(mascota: Mascota, onClick: (Mascota) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(mascota) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Nombre: ${mascota.nombre}", style = TextStyle(fontSize = 18.sp, fontFamily = FontFamily.Serif))
            Text(text = "Especie: ${mascota.especie}", style = TextStyle(fontSize = 16.sp, fontFamily = FontFamily.Serif))
            Text(text = "Raza: ${mascota.raza}", style = TextStyle(fontSize = 16.sp, fontFamily = FontFamily.Serif))
            // Puedes agregar más detalles aquí
        }
    }
}

@Composable
fun AddMascotaDialog(onDismiss: () -> Unit, onAdd: (Mascota) -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var especie by remember { mutableStateOf("") }
    var raza by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var historia by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("") }
    var tutor by remember { mutableStateOf("") }
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Agregar Mascota") },
        text = {
            Column {
                TextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
                TextField(value = especie, onValueChange = { especie = it }, label = { Text("Especie") })
                TextField(value = raza, onValueChange = { raza = it }, label = { Text("Raza") })
                TextField(value = fechaNacimiento, onValueChange = { fechaNacimiento = it }, label = { Text("Fecha de Nacimiento") })
                TextField(value = edad, onValueChange = { edad = it }, label = { Text("Edad") })
                TextField(value = historia, onValueChange = { historia = it }, label = { Text("Historia") })
                TextField(value = peso, onValueChange = { peso = it }, label = { Text("Peso") })
                TextField(value = sexo, onValueChange = { sexo = it }, label = { Text("Sexo") })
                TextField(value = tutor, onValueChange = { tutor = it }, label = { Text("Tutor") })
            }
        },
        confirmButton = {
            Button(onClick = {
                if (nombre.isNotEmpty() && especie.isNotEmpty() && raza.isNotEmpty() && fechaNacimiento.isNotEmpty() && edad.isNotEmpty() && historia.isNotEmpty() && peso.isNotEmpty() && sexo.isNotEmpty() && tutor.isNotEmpty()) {
                    onAdd(
                        Mascota(
                            nombre = nombre,
                            especie = especie,
                            raza = raza,
                            fechaNacimiento = fechaNacimiento,
                            edad = edad.toInt(),
                            historia = historia,
                            peso = peso.toDouble(),
                            sexo = sexo,
                            tutor = tutor
                        )
                    )
                    onDismiss()
                } else {
                    Toast.makeText(context, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                }
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
    MascotaListScreen(onMascotaClick = {})
}
