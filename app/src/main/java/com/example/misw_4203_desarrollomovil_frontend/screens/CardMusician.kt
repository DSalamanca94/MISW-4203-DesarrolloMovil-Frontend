package com.example.misw_4203_desarrollomovil_frontend.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.unit.dp
import com.example.misw_4203_desarrollomovil_frontend.Musicians

@Composable
fun CardMusician(
    funIdMusician: (String) -> Unit,
    funNombre: (String) -> Unit,
    musician: Musicians,
    onClick
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            Arrangement.Center
        ) {
            Text(
                text = "ID: ${musician.id}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Nombre: ${musician.name}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}