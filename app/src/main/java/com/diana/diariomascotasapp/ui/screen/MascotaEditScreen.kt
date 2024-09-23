package com.diana.diariomascotasapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.diana.diariomascotasapp.data.model.Mascota

@Composable
fun MascotaEditScreen(
    mascota: Mascota,
    onSaveClick: (Mascota) -> Unit,
    onCancelClick: () -> Unit
) {
    var nombre by remember { mutableStateOf(mascota.nombre) }
    var raza by remember { mutableStateOf(mascota.raza) }
    var edad by remember { mutableStateOf(mascota.edad.toString()) }
    var sexo by remember { mutableStateOf(mascota.sexo) }
    var peso by remember { mutableStateOf(mascota.peso.toString()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Campos para editar
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = raza,
            onValueChange = { raza = it },
            label = { Text("Raza") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = sexo,
            onValueChange = { sexo = it },
            label = { Text("Sexo") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("Peso") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Botón para guardar los cambios
        Button(onClick = {
            onSaveClick(
                mascota.copy(
                    nombre = nombre,
                    raza = raza,
                    edad = edad.toIntOrNull() ?: 0,
                    sexo = sexo,
                    peso = peso.toDoubleOrNull() ?: 0.0
                )
            )
        }) {
            Text("Guardar Cambios")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botón para cancelar la edición
        Button(onClick = onCancelClick, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)) {
            Text("Cancelar")
        }
    }
}
