package com.example.misw_4203_desarrollomovil_frontend.screens

import android.annotation.SuppressLint
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.misw_4203_desarrollomovil_frontend.Musicians
import com.example.misw_4203_desarrollomovil_frontend.MusiciansViewModel
import com.example.misw_4203_desarrollomovil_frontend.navigation.AppScreens
import coil.compose.AsyncImage

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
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleArtistas(navController: NavController, musician:Musicians){
    var id by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                { Text(text = "${musician.name}") },
                navigationIcon = {Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back", modifier = Modifier.clickable { navController.popBackStack() })})
        },
        content = {
            DetalleArtistas(musician)
        }
    )
}

@Composable
fun DetalleArtistas(musician: Musicians) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                AsyncImage(
                    model = musician.image,
                    contentDescription = musician.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .align(Alignment.CenterHorizontally),
                )
                Text(
                    text = "Descripcion",
                    modifier = Modifier
                        .padding(16.dp),
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )

                Text(
                    text = musician.description,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(18.dp),
                )
            }
        }
    }
}

