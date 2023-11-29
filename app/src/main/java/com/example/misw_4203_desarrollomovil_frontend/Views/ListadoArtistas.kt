package com.example.misw_4203_desarrollomovil_frontend.Views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.misw_4203_desarrollomovil_frontend.Models.Musicians
import com.example.misw_4203_desarrollomovil_frontend.ViewModels.Result

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListadoArtistasNav(navController: NavController, listaMusicians: LiveData<Result<List<Musicians>>>) {
    Scaffold(
        topBar = {
            TopAppBar({ Text(text = "Artistas") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable { navController.popBackStack() }
                    )
                })
        },
        content = {
            ListadoArtistas(listaMusicians = listaMusicians.value ?: Result.Success(emptyList()), navController = navController, modifier = Modifier.padding(it))
        }
    )
}

@Composable
fun ListadoArtistas(listaMusicians: Result<List<Musicians>>, navController: NavController, modifier: Modifier){
    var nombre by remember { mutableStateOf("") }
    val musiciansList = listaMusicians.getOrDefault(emptyList())
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(12.dp)
    ){
        Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight().padding(20.dp)
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ){
                    items(musiciansList){musician ->
                        CardMusician(
                            musician = musician,
                            funNombre = { nombre = it },
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}