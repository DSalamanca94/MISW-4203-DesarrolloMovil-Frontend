package com.example.misw_4203_desarrollomovil_frontend.Views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.misw_4203_desarrollomovil_frontend.Models.Collector
import com.example.misw_4203_desarrollomovil_frontend.Models.Comment
import com.example.misw_4203_desarrollomovil_frontend.ViewModels.AlbumsViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentForm(
    navController: NavController,
    viewModelA: AlbumsViewModel,
    collectors: List<Collector>,
    albumId: String // Se asume que se recibe el albumId desde el formulario anterior
) {
    var description by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(0) }
    var selectedCollectorIndex by remember { mutableStateOf(0) }

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
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = rating.toString(),
                onValueChange = { value ->
                    val newValue = value.takeIf { it.isNotEmpty() }?.toIntOrNull() ?: 0
                    rating = newValue
                },
                label = { Text("Rating") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { /* No es necesario para el collector fijo */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(collectors[selectedCollectorIndex].name)
                }

                // No es necesario el DropdownMenu para el collector fijo
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val selectedCollector = collectors.getOrNull(selectedCollectorIndex)
                val newComment = selectedCollector?.let {
                    Comment(
                        description = description,
                        rating = rating,
                        collector = it // Asignando el objeto Collector seleccionado
                    )
                }

                if (newComment != null) {
                    // Llamar a la función suspendida en el ViewModel
                    viewModelA.viewModelScope.launch {
                        try {
                            viewModelA.addCommentSuspend(albumId, newComment)
                            println("Nuevo Comentario: $newComment")
                            navController.popBackStack()
                        } catch (e: Exception) {
                            // Manejo de excepciones si es necesario
                        }
                    }
                } else {
                    // Manejo en caso de que no se pueda crear el comentario
                }
            }) {
                Text("Crear Comentario")
            }
        }
    }
}