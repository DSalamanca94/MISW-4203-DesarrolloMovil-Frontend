package com.example.misw_4203_desarrollomovil_frontend.Views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.misw_4203_desarrollomovil_frontend.Models.AlbumDto
import com.example.misw_4203_desarrollomovil_frontend.Models.Collector
import com.example.misw_4203_desarrollomovil_frontend.Models.Comment
import com.example.misw_4203_desarrollomovil_frontend.ViewModels.AlbumsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentForm(
    navController: NavController,
    viewModelA: AlbumsViewModel,
    albumId: Int
) {
    var description = remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(0) }
    var collectors by remember { mutableStateOf<List<Collector>>(emptyList()) }
    var selectedCollector by remember { mutableStateOf<Collector?>(null) }
    var isExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        try {
            collectors = viewModelA.getCollectors()
        } catch (e: Exception) {
            // Manejar el error
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Crear Comentario") },
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
                value = description.value,
                onValueChange = { description.value = it },
                label = { Text("Name") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = rating.toString(),
                onValueChange = { newRating ->
                    val enteredRating = newRating.toIntOrNull() ?: 0
                    if (enteredRating in 1..5) {
                        rating = enteredRating
                    } else {
                        rating = 0
                    }
                },
                label = { Text("Rating") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box {
                ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it }) {
                    TextField(
                        value = selectedCollector?.name ?: "",
                        onValueChange = { /* Handle if needed */ },
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                        collectors.forEach { collector ->
                            DropdownMenuItem(
                                text = { Text(text = collector.name) },
                                onClick = {
                                    selectedCollector = collector
                                    isExpanded = false
                                }
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val newComment = Comment(
                    description = description.value,
                    rating = rating,
                    collector = selectedCollector!!                )
                println("Nuevo Comentario: $newComment")
                viewModelA.addComment(albumId.toString(),newComment)
                navController.popBackStack()

            }) {
                Text("Crear Comentario")
            }
        }
    }
}

