package com.example.misw_4203_desarrollomovil_frontend.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.misw_4203_desarrollomovil_frontend.Musicians
import com.example.misw_4203_desarrollomovil_frontend.navigation.AppScreens

@Composable
fun CardMusician(
    funNombre: (String) -> Unit,
    musician: Musicians,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { musician.id?.let { id -> navController.navigate("${AppScreens.ThirdScreen}/$id") } }
        ,
        //elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            Arrangement.Center
        ) {
            AsyncImage(
                model = musician.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp).padding(10.dp).clip(RoundedCornerShape(16.dp)).align(Alignment.CenterHorizontally),
            )
            Text(
                text = musician.name,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}