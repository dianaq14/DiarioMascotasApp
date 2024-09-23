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
import com.diana.diariomascotasapp.data.Repository.RecordatorioRepository
import com.diana.diariomascotasapp.ui.screen.HomeScreen
import com.diana.diariomascotasapp.ui.screen.RecordatorioListScreen
import com.diana.diariomascotasapp.ui.screen.WelcomeScreen
import com.diana.diariomascotasapp.ui.theme.PetPlannerTheme
import com.diana.diariomascotasapp.viewModel.MascotaViewModel
import com.diana.diariomascotasapp.viewModel.MascotaViewModelFactory
import com.diana.diariomascotasapp.viewModel.RecordatorioViewModel
import com.diana.diariomascotasapp.viewModel.RecordatorioViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mascotaRepository = MascotaRepository()
        val mascotaViewModelFactory = MascotaViewModelFactory(mascotaRepository)

        val recordatorioRepository = RecordatorioRepository()
        val recordatorioViewModelFactory = RecordatorioViewModelFactory(recordatorioRepository)

        setContent {
            PetPlannerTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
                            // Crear ViewModel fuera de la composición para evitar problemas
                            val mascotaViewModel = mascotaViewModelFactory.create(MascotaViewModel::class.java)
                            HomeScreen(
                                mascotaViewModel = mascotaViewModel,
                                onNavigateToVisitas = { mascotaId ->
                                    navController.navigate("recordatorios/$mascotaId")
                                }
                            )
                        }
                        composable("recordatorios/{mascotaId}") { backStackEntry ->
                            val mascotaId = backStackEntry.arguments?.getString("mascotaId") ?: ""
                            val recordatorioViewModel = recordatorioViewModelFactory.create(RecordatorioViewModel::class.java)
                            RecordatorioListScreen(
                                mascotaId = mascotaId,
                                viewModel = recordatorioViewModel,
                                onEliminarClick = { recordatorioId ->
                                    recordatorioViewModel.eliminarRecordatorio(recordatorioId, mascotaId)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
