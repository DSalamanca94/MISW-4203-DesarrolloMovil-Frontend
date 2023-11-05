package com.example.misw_4203_desarrollomovil_frontend.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.misw_4203_desarrollomovil_frontend.MusiciansViewModel
import com.example.misw_4203_desarrollomovil_frontend.screens.DetalleArtistas
import com.example.misw_4203_desarrollomovil_frontend.screens.HomeScreen
import com.example.misw_4203_desarrollomovil_frontend.screens.ListadoArtistasNav

@Composable
fun AppNavigation(viewModel: MusiciansViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.FirstScreen.route
    ) {
        composable(route = AppScreens.FirstScreen.route) {
            HomeScreen(navController)
        }
        composable(route = AppScreens.SecondScreen.route) {
            viewModel.GetMusicians()
            if (navController !== null) {
                ListadoArtistasNav(navController, viewModel._listaMusicians)
            }
        }
        composable(route = AppScreens.ThirdScreen.route) { backStackEntry ->
            val musicianId = backStackEntry.arguments?.getInt("musicianId")
            if (musicianId != null) {
                DetalleArtistas(musicianId.toString())
            }
        }
    }
}