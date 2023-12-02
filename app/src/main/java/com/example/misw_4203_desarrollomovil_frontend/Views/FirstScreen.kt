package com.example.misw_4203_desarrollomovil_frontend.Views

import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.misw_4203_desarrollomovil_frontend.ViewModels.navigation.AppScreens


@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ArtistButton(navController)
        Spacer(modifier = Modifier.height(16.dp)) // Espacio vertical entre los botones
        AlbumButton(navController)
    }
}

@Composable
fun ArtistButton(navController: NavController) {
    Button(
        onClick = { navController.navigate(route = AppScreens.SecondScreen.route) }
    ) {
        Text("Artistas")
    }
}

@Composable
fun AlbumButton(navController: NavController) {
    Button(
        onClick = { navController.navigate(route = AppScreens.FourthScreen.route) }
    ) {
        Text("Álbumes")
    }
}