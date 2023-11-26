@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.example.misw_4203_desarrollomovil_frontend.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.misw_4203_desarrollomovil_frontend.Musicians
import coil.compose.AsyncImage
import com.example.misw_4203_desarrollomovil_frontend.Result
import androidx.compose.ui.unit.sp
import com.example.misw_4203_desarrollomovil_frontend.Album

// Extension function for Result
fun <T> Result<T>.getOrDefault(defaultValue: T): T {
    return when (this) {
        is Result.Success -> this.data
        is Result.Error -> defaultValue
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleArtistas(navController: NavController, musician: Result<Musicians>, listaAlbums: Result<List<Album>>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = musician.getOrDefault(Musicians(0, "Default", "", "", "", emptyArray())).name) },
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
            DetalleArtistasContent(musician = musician, listaAlbumsResult = listaAlbums, modifier = Modifier.padding(it))
        }
    )
}

@Composable
@Preview(showBackground = true)
fun DetalleArtistasContent(musician: Result<Musicians>, listaAlbumsResult: Result<List<Album>>,modifier: Modifier) {
    val artist = musician.getOrDefault(Musicians(0, "Default", "", "", "", emptyArray()))
    val listaAlbums: List<Album> = when (listaAlbumsResult) {
        is Result.Success -> listaAlbumsResult.data
        is Result.Error -> emptyList()  // O manejar el error de alguna manera si es necesario
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = artist.image,
            contentDescription = artist.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
        )
        Text(
            text = "Descripción",
            modifier = Modifier
                .padding(16.dp),
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
        )
        Text(
            text = artist.description,
            modifier = Modifier
                .padding(18.dp),
        )
        Text(
            text = "Añadir un Album",
            modifier = Modifier
                .padding(16.dp),
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
        )
        LazyRow (
            modifier = Modifier.fillMaxWidth()
        ) {
            items(listaAlbums) {
                album -> AlbumItem(album = album)
            }
        }
    }
}

@Composable
fun AlbumItem(album: Album) {
    Text(text = album.name, style = TextStyle(fontSize = 20.sp));
}