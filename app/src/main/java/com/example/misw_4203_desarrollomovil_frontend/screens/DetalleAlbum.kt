package com.example.misw_4203_desarrollomovil_frontend.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.misw_4203_desarrollomovil_frontend.AlbumList
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleAlbum(navController: NavController, album: AlbumList){
    var id by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                { Text(text = "${album.name}") },
                navigationIcon = {Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back", modifier = Modifier.clickable { navController.popBackStack() })})
        },
        content = {
            DetalleAlbum(album)
        }
    )
}
@Composable
fun DetalleAlbum(album: AlbumList) {
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
                    model = album.cover,
                    contentDescription = album.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .align(Alignment.CenterHorizontally),
                )
                Text(
                    text = "Nombre",
                    modifier = Modifier
                        .padding(16.dp),
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )

                Text(
                    text = album.name,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(18.dp),
                )
                Text(
                    text = "Release Date",
                    modifier = Modifier
                        .padding(16.dp),
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )

                Text(
                    text = album.releaseDate,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(18.dp),
                )
                Text(
                    text = "Description",
                    modifier = Modifier
                        .padding(16.dp),
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )

                Text(
                    text = album.description,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(18.dp),
                )
                Text(
                    text = "Genero",
                    modifier = Modifier
                        .padding(16.dp),
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )

                Text(
                    text = album.genre,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(18.dp),
                )
                Text(
                    text = "Record LAbel",
                    modifier = Modifier
                        .padding(16.dp),
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )

                Text(
                    text = album.recordLabel,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(18.dp),
                )
            }
        }
    }
}