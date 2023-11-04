package com.example.misw_4203_desarrollomovil_frontend.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.misw_4203_desarrollomovil_frontend.Musicians
import com.example.misw_4203_desarrollomovil_frontend.MusiciansViewModel
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListadoArtistas(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar({ Text(text = "Listado Artistas") }, navigationIcon = {Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back", modifier = Modifier.clickable { navController.popBackStack() })})
        },
        content = {
            BodyContent(navController);
        }
    )
}*/
@Composable
fun DetalleArtistas(listaMusicians: ArrayList<Musicians>, viewModel: MusiciansViewModel){
    var id by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(12.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ){
                items(listaMusicians){musician ->
                    CardMusician(
                        musician = musician,
                        funIdMusician = {id = it},
                        funNombre = { nombre = it },
                        onClick = {
                            navController.navigate("musicianDetail/${musician.id}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BodyContent(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally, // Centra el contenido horizontalmente
    ) {
        Text(text = "Esto es un artista")
    }
}