package com.example.misw_4203_desarrollomovil_frontend.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.misw_4203_desarrollomovil_frontend.MusiciansViewModel
import com.example.misw_4203_desarrollomovil_frontend.AlbumsViewModel
import com.example.misw_4203_desarrollomovil_frontend.Formulario
import com.example.misw_4203_desarrollomovil_frontend.screens.DetalleAlbum
import com.example.misw_4203_desarrollomovil_frontend.screens.DetalleArtistas
import com.example.misw_4203_desarrollomovil_frontend.screens.HomeScreen
import com.example.misw_4203_desarrollomovil_frontend.screens.ListadoAlbumesNav
import com.example.misw_4203_desarrollomovil_frontend.screens.ListadoArtistasNav


@Composable
fun AppNavigation(viewModel: MusiciansViewModel, viewModelA: AlbumsViewModel) {
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

        composable(route = AppScreens.FourthScreen.route) {
            viewModelA.getAlbumes()
            if (navController !== null) {
                ListadoAlbumesNav(navController, viewModelA._listaAlbumes)
            }
        }

        composable(route = AppScreens.FifthScreen.route) {
            val navBackStackEntry = navController.currentBackStackEntry ?: return@composable
            val currentRoute = navBackStackEntry?.destination?.route

            if (currentRoute == AppScreens.FifthScreen.route) {
                Formulario(navController = navController, viewModelA = viewModelA)
            }
        }

        composable(
            route = "${AppScreens.SixthScreen}/{albumId}",
            arguments = listOf(navArgument("albumId") { type = NavType.IntType })
        ) { backStackEntry ->
            val albumId = backStackEntry.arguments?.getInt("albumId")
            viewModelA.GetAlbumbyId(albumId.toString())

            if (albumId != null) {
                DetalleAlbum(navController, viewModelA._detalleAlbum)
            }
        }

    }
}


