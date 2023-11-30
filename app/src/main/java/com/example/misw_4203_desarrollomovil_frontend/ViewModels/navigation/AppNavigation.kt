package com.example.misw_4203_desarrollomovil_frontend.ViewModels.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.misw_4203_desarrollomovil_frontend.Models.Collector
import com.example.misw_4203_desarrollomovil_frontend.Views.Formulario
import com.example.misw_4203_desarrollomovil_frontend.Views.DetalleAlbum
import com.example.misw_4203_desarrollomovil_frontend.Views.DetalleArtistas
import com.example.misw_4203_desarrollomovil_frontend.Views.HomeScreen
import com.example.misw_4203_desarrollomovil_frontend.Views.ListadoAlbumesNav
import com.example.misw_4203_desarrollomovil_frontend.Views.ListadoArtistasNav
import com.example.misw_4203_desarrollomovil_frontend.Views.ListadoTracksNav
import com.example.misw_4203_desarrollomovil_frontend.ViewModels.AlbumsViewModel
import com.example.misw_4203_desarrollomovil_frontend.ViewModels.MusiciansViewModel
import com.example.misw_4203_desarrollomovil_frontend.Views.CommentForm

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
            viewModel.getMusicians()
            if (navController !== null) {
                ListadoArtistasNav(navController, viewModel.listaMusicians)
            }
        }
        composable(
            route = "${AppScreens.ThirdScreen}/{musicianId}",
            arguments = listOf(navArgument("musicianId") { type = NavType.IntType })
        ) { backStackEntry ->
            val musicianId = backStackEntry.arguments?.getInt("musicianId")
            viewModel.getMusiciansById(musicianId.toString())

            if (musicianId != null) {
                DetalleArtistas(navController, viewModel.detalleMusician)
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

        composable(
            route = "${AppScreens.SeventhScreen}/{albumId}",
            arguments = listOf(navArgument("albumId") { type = NavType.IntType })
        ) {backStackEntry ->
            val albumId = backStackEntry.arguments?.getInt("albumId")
            viewModelA.getTracks(albumId.toString())
            if (albumId !== null) {
                ListadoTracksNav(navController, viewModelA._listaTracks)
            }
        }

        composable(
            route = "${AppScreens.EigthScreen.route}/{albumId}",
            arguments = listOf(navArgument("albumId") { type = NavType.StringType })
        ) { backStackEntry ->
            val albumId = backStackEntry.arguments?.getString("albumId")
            albumId?.let {
                CommentForm(
                    navController = navController,
                    viewModelA = viewModelA,
                    collectors = listOf(), 
                    albumId = it
                )
            } ?: navController.popBackStack()
        }
    }
}


