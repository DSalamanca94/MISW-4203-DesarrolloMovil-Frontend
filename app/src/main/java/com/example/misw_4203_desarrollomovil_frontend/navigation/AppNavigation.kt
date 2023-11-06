package com.example.misw_4203_desarrollomovil_frontend.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
        composable(
            route = "${AppScreens.ThirdScreen}/{musicianId}",
            arguments = listOf(navArgument("musicianId") { type = NavType.IntType })
        ) { backStackEntry ->
            val musicianId = backStackEntry.arguments?.getInt("musicianId")
            viewModel.GetMusiciansbyId(musicianId.toString())

            if (musicianId != null) {
                DetalleArtistas(navController, viewModel._detalleMusician)
            }
        }
    }
}