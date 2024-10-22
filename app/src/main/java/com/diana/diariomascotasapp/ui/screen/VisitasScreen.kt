package com.diana.diariomascotasapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diana.diariomascotasapp.data.model.Visita
import com.diana.diariomascotasapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitasVeterinarioScreen() {
    var visitas by remember { mutableStateOf(getDefaultVisitas()) }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Visitas Veterinarias") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF1C491E),
                    titleContentColor = Color(0xFFFFFFFF)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = Color(0xFF1A54AF),
                contentColor = Color(0xFFFFFFFF)
            ) {
                Text("+")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Lista de Visitas Veterinarias", style = MaterialTheme.typography.titleMedium)

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(visitas.size) { index ->
                    VisitaVeterinariaItem(visita = visitas[index])
                }
            }

            if (showDialog) {
                AddVisitaDialog(
                    onDismiss = { showDialog = false },
                    onAddVisita = { newVisita ->
                        visitas = visitas + newVisita
                        showDialog = false
                    }
                )
            }
        }
    }
}

@Composable
fun VisitaVeterinariaItem(visita: Visita) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text("Motivo: ${visita.motivo}", style = MaterialTheme.typography.bodyLarge)
        Text("Fecha: ${visita.fecha}", style = MaterialTheme.typography.bodyMedium)
        Text("Detalles: ${visita.detalles}", style = MaterialTheme.typography.bodySmall)
        Text("Notas: ${visita.notas}", style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddVisitaDialog(onDismiss: () -> Unit, onAddVisita: (Visita) -> Unit) {
    var mascotaId by remember { mutableStateOf("") }
    var motivo by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var detalles by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Agregar Visita Veterinaria") },
        text = {
            Column {
                BasicTextField(
                    value = mascotaId,
                    onValueChange = { mascotaId = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    decorationBox = { innerTextField ->
                        if (mascotaId.isEmpty()) {
                            Text(text = "ID de Mascota")
                        }
                        innerTextField()
                    }
                )
                BasicTextField(
                    value = motivo,
                    onValueChange = { motivo = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    decorationBox = { innerTextField ->
                        if (motivo.isEmpty()) {
                            Text(text = "Motivo")
                        }
                        innerTextField()
                    }
                )
                BasicTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    decorationBox = { innerTextField ->
                        if (fecha.isEmpty()) {
                            Text(text = "Fecha (dd/MM/yyyy)")
                        }
                        innerTextField()
                    }
                )
                BasicTextField(
                    value = detalles,
                    onValueChange = { detalles = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    decorationBox = { innerTextField ->
                        if (detalles.isEmpty()) {
                            Text(text = "Detalles")
                        }
                        innerTextField()
                    }
                )
                BasicTextField(
                    value = notas,
                    onValueChange = { notas = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    decorationBox = { innerTextField ->
                        if (notas.isEmpty()) {
                            Text(text = "Notas")
                        }
                        innerTextField()
                    }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val newVisita = Visita(
                        mascotaId = mascotaId.toInt(),
                        motivo = motivo,
                        fecha = fecha,
                        detalles = detalles,
                        notas = notas,
                        veterinario = "Dra Laura"
                    )
                    onAddVisita(newVisita)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1A54AF),
                    contentColor = Color(0xFFFFFFFF)
                )
            ) {
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

fun getDefaultVisitas(): List<Visita> {
    return listOf(
        Visita(
            mascotaId = 1,
            motivo = "Vacunación Anual",
            fecha = "15/01/2023",
            detalles = "Vacuna antirrábica aplicada.",
            notas = "Próxima vacuna en 1 año.",
            veterinario = "Dra Ana Maria"
        ),
        Visita(
            mascotaId = 2,
            motivo = "Desparasitación Trimestral",
            fecha = "10/03/2023",
            detalles = "Desparasitación realizada con comprimidos.",
            notas = "Próxima desparasitación en 3 meses.",
            veterinario = "Dra Katherine"
        ),
        Visita(
            mascotaId = 3,
            motivo = "Baño y Peluquería",
            fecha = "05/04/2023",
            detalles = "Baño completo y corte de uñas realizado.",
            notas = "Revisar orejas en la próxima visita.",
            veterinario = "Dra Laura"
        )
    )
}

@Preview(showBackground = true)
@Composable
fun VisitasVeterinarioScreenPreview() {
    PetPlannerTheme(darkTheme = true) {
        VisitasVeterinarioScreen()
    }
}
