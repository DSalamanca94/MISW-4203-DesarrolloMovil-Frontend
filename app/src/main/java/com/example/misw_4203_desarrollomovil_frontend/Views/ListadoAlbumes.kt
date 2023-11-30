package com.example.misw_4203_desarrollomovil_frontend.Views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import com.example.misw_4203_desarrollomovil_frontend.Models.AlbumList
import com.example.misw_4203_desarrollomovil_frontend.ViewModels.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListadoAlbumesNav(navController: NavController, listaAlbumes: ArrayList<AlbumList>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Álbumes") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back")
                    }
                },
                actions = {
                    Spacer(modifier = Modifier.weight(1f))
                    FloatingActionButton(
                        onClick = {
                            navController.navigate(AppScreens.FifthScreen.route)
                        },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Create Album"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            )

        },
        content = {
            ListadoAlbumes(navController, listaAlbumes)
        },
    )
}


@Composable
fun ListadoAlbumes(navController: NavController, listaAbumes: ArrayList<AlbumList>){
    var nombre by remember { mutableStateOf("") }
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
                    items(listaAbumes){album ->
                        CardAlbum(
                            album = album,
                            funName = { nombre = it },
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}