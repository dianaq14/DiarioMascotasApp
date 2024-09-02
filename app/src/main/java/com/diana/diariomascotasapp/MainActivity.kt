package com.diana.diariomascotasapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diana.diariomascotasapp.data.repository.MascotaRepository
import com.diana.diariomascotasapp.ui.screen.HomeScreen
import com.diana.diariomascotasapp.ui.screen.WelcomeScreen
import com.diana.diariomascotasapp.ui.theme.PetPlannerTheme
import com.diana.diariomascotasapp.viewModel.MascotaViewModel
import com.diana.diariomascotasapp.viewModel.MascotaViewModelFactory
import com.diana.diariomascotasapp.data.dao.MascotaDatabase

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtener la instancia de la base de datos y el DAO
        val database = MascotaDatabase.getDatabase(applicationContext)
        val mascotaDao = database.mascotaDao()

        // Crear instancia del repositorio y ViewModelFactory
        val mascotaRepository = MascotaRepository(mascotaDao)
        val mascotaViewModelFactory = MascotaViewModelFactory(mascotaRepository)

        // Configurar el contenido
        setContent {
            PetPlannerTheme(darkTheme = true) { // Modo oscuro predeterminado
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Configurar el controlador de navegación
                    navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "welcome") {
                        composable("welcome") {
                            WelcomeScreen(
                                onStartClick = {
                                    navController.navigate("home")
                                }
                            )
                        }
                        composable("home") {
                            HomeScreen(
                                mascotaViewModel = mascotaViewModelFactory.create(MascotaViewModel::class.java),
                                onNavigateToVisitas = {
                                    // Implementa la navegación a otras pantallas si es necesario
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
