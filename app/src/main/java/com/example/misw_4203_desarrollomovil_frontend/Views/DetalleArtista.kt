package com.example.misw_4203_desarrollomovil_frontend.Views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.misw_4203_desarrollomovil_frontend.Models.Musicians
import coil.compose.AsyncImage
import com.example.misw_4203_desarrollomovil_frontend.Models.Album
import com.example.misw_4203_desarrollomovil_frontend.Models.AlbumList
import com.example.misw_4203_desarrollomovil_frontend.ViewModels.AlbumsViewModel
import com.example.misw_4203_desarrollomovil_frontend.ViewModels.Result

// Extension function for Result
fun <T> Result<T>.getOrDefault(defaultValue: T): T {
    return when (this) {
        is Result.Success -> this.data
        is Result.Error -> defaultValue
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleArtistas(navController: NavController, musician: Result<Musicians>, listaAlbums: Result<List<Album>>, listaAlbumes: ArrayList<AlbumList>, viewModelA: AlbumsViewModel) {
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
            DetalleArtistasContent(musician = musician, listaAlbumsResult = listaAlbums, listaAlbumes = listaAlbumes, viewModelA = viewModelA,   modifier = Modifier.padding(it))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleArtistasContent(musician: Result<Musicians>, listaAlbumsResult: Result<List<Album>>, listaAlbumes: ArrayList<AlbumList>, viewModelA: AlbumsViewModel,  modifier: Modifier) {
    val artist = musician.getOrDefault(Musicians(0, "Default", "", "", "", emptyArray()))
    val listaAlbums: List<Album> = when (listaAlbumsResult) {
        is Result.Success -> listaAlbumsResult.data
        is Result.Error -> emptyList()  // O manejar el error de alguna manera si es necesario
    }
    var popupControl by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false)}
    val selectedAlbum = remember { mutableStateOf<Int?>(null) }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
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
                modifier = Modifier.padding(5.dp)
            ) {
                items(listaAlbums) {
                        album -> AlbumItem(album = album)
                }
            }
        }
        Button(
            onClick = { popupControl = true },
            modifier = Modifier
                .size(width = 80.dp, height = 80.dp)
                .padding(8.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.White)
        }
        if (popupControl) {
            AlertDialog(
                title = {
                    Text(text = "Seleccione el álbum a añadir")
                },
                text = {
                    Box {
                        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it }) {
                            TextField(
                                value = selectedAlbum.value?.let { id ->
                                    listaAlbumes.find { it.id == id }?.name ?: ""
                                } ?: "",
                                onValueChange = { /* Handle if needed */ },
                                readOnly = true,
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                                modifier = Modifier.menuAnchor()
                            )

                            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                                listaAlbumes.forEach { album ->
                                    DropdownMenuItem(
                                        text = { Text(text = album.name) },
                                        onClick = {
                                            selectedAlbum.value = album.id
                                            isExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                },
                onDismissRequest = {
                    popupControl = false;
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            viewModelA.addAlbumToMusician(selectedAlbum.value.toString(), artist.id.toString());
                            popupControl = false;
                        }
                    ) {
                        Text("Confirmar")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            popupControl = false;
                        }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
            println("selectedAlbum: ${selectedAlbum.value}")
        }
    }
}

@Composable
fun AlbumItem(album: Album) {
    AsyncImage(
        model = album.cover,
        contentDescription = album.name,
        contentScale = ContentScale.FillHeight,
        modifier = Modifier
            .size(width = 200.dp, height = 200.dp),
    )
}