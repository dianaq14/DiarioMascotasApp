package com.diana.diariomascotasapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.diana.diariomascotasapp.data.model.Mascota
import com.diana.diariomascotasapp.ui.theme.PetPlannerTheme
import com.diana.diariomascotasapp.viewModel.MascotaUiState
import com.diana.diariomascotasapp.viewModel.MascotaViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    mascotaViewModel: MascotaViewModel,
    onNavigateToVisitas: (Any?) -> Unit
) {
    // Obtener el estado de la UI desde el ViewModel
    val uiState by mascotaViewModel.uiState.collectAsState()

    // Variables locales para el diálogo de agregar mascota
    var showDialog by remember { mutableStateOf(false) }
    var mascotaNombre by remember { mutableStateOf("") }
    var mascotaEspecie by remember { mutableStateOf("") }
    var mascotaRaza by remember { mutableStateOf("") }
    var mascotaFechaNacimiento by remember { mutableStateOf("") }
    var mascotaFotoUri by remember { mutableStateOf("") }
    var mascotaEdad by remember { mutableStateOf("") } // Int expected, so we will convert this later
    var mascotaHistoria by remember { mutableStateOf("") }
    var mascotaPeso by remember { mutableStateOf("") } // Double expected, convert later
    var mascotaSexo by remember { mutableStateOf("") }
    var mascotaTutor by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pet Planner") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar Mascota")
            }
        }
    ) { innerPadding ->
        // Pantalla principal basada en el estado de la UI
        when (uiState) {
            is MascotaUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is MascotaUiState.Success -> {
                val mascotas = (uiState as MascotaUiState.Success).mascotas
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp)
                ) {
                    Text(text = "Lista de Mascotas", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(8.dp))

                    // Mostrar la lista de mascotas
                    mascotas.forEach { mascota ->
                        Text(
                            text = "${mascota.nombre} - ${mascota.especie} - ${mascota.raza}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }

            is MascotaUiState.Error -> {
                val errorMessage = (uiState as MascotaUiState.Error).message
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error: $errorMessage", color = MaterialTheme.colorScheme.error)
                }
            }

            else -> {
                // Manejo de otros estados
            }
        }
    }

    // Diálogo para agregar mascota
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Agregar Mascota") },
            text = {
                Column {
                    OutlinedTextField(
                        value = mascotaNombre,
                        onValueChange = { mascotaNombre = it },
                        label = { Text("Nombre") }
                    )
                    OutlinedTextField(
                        value = mascotaEspecie,
                        onValueChange = { mascotaEspecie = it },
                        label = { Text("Especie") }
                    )
                    OutlinedTextField(
                        value = mascotaRaza,
                        onValueChange = { mascotaRaza = it },
                        label = { Text("Raza") }
                    )
                    OutlinedTextField(
                        value = mascotaFechaNacimiento,
                        onValueChange = { mascotaFechaNacimiento = it },
                        label = { Text("Fecha de Nacimiento") }
                    )
                    OutlinedTextField(
                        value = mascotaFotoUri,
                        onValueChange = { mascotaFotoUri = it },
                        label = { Text("Foto URI") }
                    )
                    OutlinedTextField(
                        value = mascotaEdad,
                        onValueChange = { mascotaEdad = it },
                        label = { Text("Edad") }
                    )
                    OutlinedTextField(
                        value = mascotaHistoria,
                        onValueChange = { mascotaHistoria = it },
                        label = { Text("Historia") }
                    )
                    OutlinedTextField(
                        value = mascotaPeso,
                        onValueChange = { mascotaPeso = it },
                        label = { Text("Peso") }
                    )
                    OutlinedTextField(
                        value = mascotaSexo,
                        onValueChange = { mascotaSexo = it },
                        label = { Text("Sexo") }
                    )
                    OutlinedTextField(
                        value = mascotaTutor,
                        onValueChange = { mascotaTutor = it },
                        label = { Text("Tutor") }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val newMascota = Mascota(
                            nombre = mascotaNombre,
                            especie = mascotaEspecie,
                            raza = mascotaRaza,
                            fechaNacimiento = mascotaFechaNacimiento,
                            fotoUri = mascotaFotoUri,
                            edad = mascotaEdad.toIntOrNull() ?: 0, // Convertir edad a Int
                            historia = mascotaHistoria,
                            peso = mascotaPeso.toDoubleOrNull() ?: 0.0, // Convertir peso a Double
                            sexo = mascotaSexo,
                            tutor = mascotaTutor
                        )
                        mascotaViewModel.addMascota(newMascota) // Agregar la nueva mascota
                        showDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1A54AF), // Color de fondo FF1A54AF
                        contentColor = Color.White // Color del texto en blanco
                    ),
                    shape = RoundedCornerShape(16.dp) // Bordes redondeados de 16dp
                ) {
                    Text(
                        text = "Agregar",
                        fontSize = 18.sp, // Tamaño de la letra
                        style = MaterialTheme.typography.bodyLarge.copy( // Estilo personalizado
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1A54AF), // Color de fondo FF1A54AF
                        contentColor = Color.White // Color del texto en blanco
                    ),
                    shape = RoundedCornerShape(16.dp) // Bordes redondeados de 16dp
                ) {
                    Text(
                        text = "Cancelar",
                        fontSize = 18.sp, // Tamaño de la letra
                        style = MaterialTheme.typography.bodyLarge.copy( // Estilo personalizado
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }
            }
        )
    }
}

/*@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PetPlannerTheme(darkTheme = true) {
        // Creamos un Mock del estado de UI con datos ficticios
        val mockUiState = MascotaUiState.Success(
            listOf(
                Mascota(
                    nombre = "Isis",
                    especie = "Perro",
                    raza = "Pug",
                    fechaNacimiento = "12/09/2014",
                    fotoUri = "",
                    edad = 9,
                    historia = "Una historia adorable",
                    peso = 7.5,
                    sexo = "Hembra",
                    tutor = "Diana"
                ),
                Mascota(
                    nombre = "Liam",
                    especie = "Perro",
                    raza = "Chihuahua",
                    fechaNacimiento = "01/01/2023",
                    fotoUri = "",
                    edad = 1,
                    historia = "Joven y juguetón",
                    peso = 3.2,
                    sexo = "Macho",
                    tutor = "Diana"
                )
            )
        )

        // Creamos un Mock del repository
        val mockRepository = object : MascotaRepository() {
            override suspend fun getMascotas(): List<Mascota> {
                // Simulamos un resultado para la función suspendida
                return runBlocking {
                    mockUiState.mascotas
                }
            }

            override suspend fun addMascota(mascota: Mascota) {
                // No es necesario hacer nada aquí para el preview
            }

            override suspend fun updateMascota(id: String, mascota: Mascota) {
                // No es necesario hacer nada aquí para el preview
            }

            override suspend fun deleteMascota(id: String) {
                // No es necesario hacer nada aquí para el preview
            }
        }

        // Instanciamos el ViewModel con el MockRepository
        val mockViewModel = MascotaViewModel(mockRepository)

        // Llamamos a la pantalla con el ViewModel simulado
        HomeScreen(mascotaViewModel = mockViewModel, onNavigateToVisitas = {})
    }
}*/
