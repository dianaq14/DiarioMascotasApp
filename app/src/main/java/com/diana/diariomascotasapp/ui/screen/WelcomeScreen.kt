package com.diana.diariomascotasapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.content.contentReceiver
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diana.diariomascotasapp.R
import com.diana.diariomascotasapp.ui.theme.PetPlannerTheme

@Composable
fun WelcomeScreen(onStartClick: () -> Unit) {
    val backgroundColor = MaterialTheme.colorScheme.background
    val contentColor = MaterialTheme.colorScheme.onBackground

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo de la app
            Image(
                painter = painterResource(id = R.drawable.petdiary),
                contentDescription = "App Logo",
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Nombre de la app
            Text(
                text = "Pet Planner",
                style = MaterialTheme.typography.displayLarge.copy(color = contentColor)
            )
        }

        // Botón para iniciar la app en la parte inferior
        Button(
            onClick = onStartClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .wrapContentWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1A54AF), // Fondo verde del botón
                contentColor = Color.White // Color del texto
            ),
            shape = RoundedCornerShape(16.dp) // Bordes redondeados de 16dp
        ) {
            Text(
                text = "Empezar",
                fontSize = 18.sp,
                style = MaterialTheme.typography.displayMedium.copy(color = contentColor)// Tamaño de la letra
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    PetPlannerTheme(darkTheme = true) {
        WelcomeScreen(onStartClick = { /* Acción al iniciar */ })
    }
}
