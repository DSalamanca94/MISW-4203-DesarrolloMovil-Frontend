package com.example.misw_4203_desarrollomovil_frontend.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

import  androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.misw_4203_desarrollomovil_frontend.AlbumDto
import com.example.misw_4203_desarrollomovil_frontend.AlbumsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Formulario(navController: NavController, viewModelA: AlbumsViewModel) {
    val name = remember { mutableStateOf("") }
    val cover = remember { mutableStateOf("") }
    var releaseDate =remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val genre = remember { mutableStateOf("") }
    val recordLabel = remember { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false)}
    var isExpandedRecord by remember { mutableStateOf(false)}

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Crear Álbum") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(32.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            TextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Name") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = cover.value,
                onValueChange = { cover.value = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                label = { Text("Cover") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = releaseDate.value,
                onValueChange = { releaseDate.value = it },
                label = { Text("Release Date (yyy-mm-dd)") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = description.value,
                onValueChange = { description.value = it },
                label = { Text("Description") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it }) {
                TextField(
                    value = genre.value,
                    onValueChange = { genre.value = it },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                    DropdownMenuItem(
                        text = {Text(text = "Classical")},
                        onClick = {
                            genre.value =  "Classical"
                            isExpanded = false}
                    )
                    DropdownMenuItem(
                        text = {Text(text = "Salsa")},
                        onClick = {
                            genre.value =  "Salsa"
                            isExpanded = false}
                    )
                    DropdownMenuItem(
                        text = {Text(text = "Rock")},
                        onClick = {
                            genre.value =  "Rock"
                            isExpanded = false}
                    )
                    DropdownMenuItem(
                        text = {Text(text = "Folk")},
                        onClick = {
                            genre.value =  "Folk"
                            isExpanded = false}
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(expanded = isExpandedRecord, onExpandedChange = { isExpandedRecord = it }) {
                TextField(
                    value = recordLabel.value,
                    onValueChange = { recordLabel.value = it },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedRecord) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(expanded = isExpandedRecord, onDismissRequest = { isExpandedRecord = false }) {
                    DropdownMenuItem(
                        text = {Text(text = "Sony Music")},
                        onClick = {
                            recordLabel.value =  "Sony Music"
                            isExpandedRecord = false}
                    )
                    DropdownMenuItem(
                        text = {Text(text = "EMI")},
                        onClick = {
                            recordLabel.value =  "EMI"
                            isExpandedRecord = false}
                    )
                    DropdownMenuItem(
                        text = {Text(text = "Discos Fuentes")},
                        onClick = {
                            recordLabel.value =  "Discos Fuentes"
                            isExpandedRecord = false}
                    )
                    DropdownMenuItem(
                        text = {Text(text = "Elektra")},
                        onClick = {
                            recordLabel.value =  "Elektra"
                            isExpandedRecord = false}
                    )
                    DropdownMenuItem(
                        text = {Text(text = "Fania Records")},
                        onClick = {
                            recordLabel.value =  "Fania Records"
                            isExpandedRecord = false}
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val newAlbum = AlbumDto(
                    name = name.value,
                    cover = cover.value,
                    releaseDate = releaseDate.value,
                    description = description.value,
                    genre = genre.value,
                    recordLabel = recordLabel.value
                )
                viewModelA.addAlbum(newAlbum)
                println("Nuevo álbum: $newAlbum")
                navController.popBackStack()
            }) {
                Text("Crear Álbum")
            }
        }
    }
}


