package com.diana.diariomascotasapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
    onEditClick: () -> Unit,
    onVeterinaryHistoryClick: () -> Unit,
    onDietClick: () -> Unit
) {
    // Fondo de la pantalla
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)) // Fondo color #121212
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Card para la imagen de la mascota
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            backgroundColor = Color(0xFF1E1E1E), // Fondo de la Card (surface)
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                mascota.fotoUri?.let {
                    Image(
                        painter = rememberImagePainter(it),
                        contentDescription = "Foto de la mascota",
                        modifier = Modifier
                            .size(160.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } ?: Text(
                    "No hay foto disponible",
                    modifier = Modifier.padding(16.dp),
                    color = Color.White // Texto blanco
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Card para los detalles de la mascota
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color(0xFF1E1E1E), // Fondo de la Card (surface)
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = mascota.nombre, style = MaterialTheme.typography.h5, color = Color.White)
                Spacer(modifier = Modifier.height(8.dp))
                DetailRow(label = "Raza:", value = mascota.raza)
                DetailRow(label = "Edad:", value = "${mascota.edad} años")
                DetailRow(label = "Sexo:", value = mascota.sexo)
                DetailRow(label = "Peso:", value = "${mascota.peso} kg")
                DetailRow(label = "Fecha de nacimiento:", value = mascota.fechaNacimiento)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para ver el historial veterinario
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color(0xFF1E1E1E), // Fondo de la Card (surface)
            elevation = 4.dp
        ) {
            Button(
                onClick = { onVeterinaryHistoryClick() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF1A54AF), // Color del fondo del botón
                    contentColor = Color.White // Color del texto del botón
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Ver Historial Veterinario")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botón para ver el registro de dieta
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color(0xFF1E1E1E), // Fondo de la Card (surface)
            elevation = 4.dp
        ) {
            Button(
                onClick = { onDietClick() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF1A54AF), // Color del fondo del botón
                    contentColor = Color.White // Color del texto del botón
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Ver Registro de Dieta")
            }
        }

        // Botón para visitas veterinario
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color(0xFF1E1E1E), // Fondo de la Card (surface)
            elevation = 4.dp
        ) {
            Button(
                onClick = { onDietClick() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF1A54AF), // Color del fondo del botón
                    contentColor = Color.White // Color del texto del botón
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Visitas Veterinario")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para editar
        Button(
            onClick = { onEditClick() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF1A54AF), // Color del fondo del botón
                contentColor = Color.White // Color del texto del botón
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
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
        Text(text = label, style = MaterialTheme.typography.body1, color = Color(0xFFB0B0B0)) // Gris claro
        Text(text = value, style = MaterialTheme.typography.body1, color = Color.White) // Blanco
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
        onEditClick = {},
        onVeterinaryHistoryClick = {},
        onDietClick = {}
    )
}


