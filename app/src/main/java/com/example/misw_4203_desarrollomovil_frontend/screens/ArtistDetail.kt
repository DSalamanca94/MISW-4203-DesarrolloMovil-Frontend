package com.example.misw_4203_desarrollomovil_frontend.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistDetail(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                { Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                        .background(Color.Blue)
                ) },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable { navController.popBackStack() }
                    )
                }
            )
        },
        content = {
            DeatilBodyContent(navController)
        }
    )
}

@Composable
fun DeatilBodyContent(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top, // Inicio del contenido en la parte superior
        horizontalAlignment = Alignment.Start, // Alineación izquierda
    ) {
        Text(
            text = "Shakira",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .background(Color.Blue)
        )
        Text(
            text = "Fecha de Nacimiento",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Start, // Alineación del texto a la izquierda
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "2 de febrero de 1977",
            style = TextStyle(fontSize = 18.sp),
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Bibliografia",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Es una de las mejores artistas de Colombia. Nacida en Barranquilla con el pelo negro y ahora es mona.",
            style = TextStyle(fontSize = 18.sp),
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(16.dp)
        )
    }
}

