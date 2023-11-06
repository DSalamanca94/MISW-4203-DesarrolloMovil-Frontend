package com.example.misw_4203_desarrollomovil_frontend.screens

import androidx.compose.material3.Button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.misw_4203_desarrollomovil_frontend.navigation.AppScreens

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally, // Centra el contenido horizontalmente
    ) {
        ArtistButton(navController)
    }
}

@Composable
fun ArtistButton(navController: NavController) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        // viewModel.GetMusicians()
        // ListadoArtistas(viewModel._listaMusicians , viewModel ,navController)
        Button(
            onClick = { navController.navigate(route = AppScreens.SecondScreen.route) }
        ) {
            Text("Artistas")
        }
    }
}