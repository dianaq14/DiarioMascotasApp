package com.diana.diariomascotasapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.diana.diariomascotasapp.data.model.Mascota

@Composable
fun MascotaDetailScreen(
    mascota: Mascota,
    onEditClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Imagen de la mascota
        mascota.fotoUri?.let {
            Image(
                painter = rememberImagePainter(it),
                contentDescription = "Foto de la mascota",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } ?: Text("No hay foto disponible", modifier = Modifier.padding(16.dp))

        Spacer(modifier = Modifier.height(16.dp))

        // Nombre de la mascota
        Text(text = mascota.nombre, style = MaterialTheme.typography.h5)

        Spacer(modifier = Modifier.height(8.dp))

        // Detalles adicionales de la mascota
        DetailRow(label = "Raza:", value = mascota.raza)
        DetailRow(label = "Edad:", value = "${mascota.edad} años")
        DetailRow(label = "Sexo:", value = mascota.sexo)
        DetailRow(label = "Peso:", value = "${mascota.peso} kg")
        DetailRow(label = "Fecha de nacimiento:", value = mascota.fechaNacimiento)

        Spacer(modifier = Modifier.height(32.dp))

        // Botón para editar
        Button(onClick = { onEditClick() }) {
            Text("Editar Información")
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.body1, color = Color.Gray)
        Text(text = value, style = MaterialTheme.typography.body1)
    }
}

@Preview(showBackground = true)
@Composable
fun MascotaDetailScreenPreview() {
    MascotaDetailScreen(
        mascota = Mascota(
            nombre = "Isis",
            raza = "Pug",
            edad = 9,
            sexo = "Hembra",
            peso = 6.5,
            fechaNacimiento = "12/09/2014",
            fotoUri = null,
            especie = "Perro",
            historia = "Regalo",
            tutor = "Diana"
        ),
        onEditClick = {}
    )
}

