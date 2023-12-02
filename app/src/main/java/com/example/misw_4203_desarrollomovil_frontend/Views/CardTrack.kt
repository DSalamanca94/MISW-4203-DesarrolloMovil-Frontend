package com.example.misw_4203_desarrollomovil_frontend.Views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.misw_4203_desarrollomovil_frontend.Models.TrackList
import com.example.misw_4203_desarrollomovil_frontend.ViewModels.navigation.AppScreens

@Composable
fun CardTrack(
    funName: (String) -> Unit,
    track: TrackList,
    navController: NavController
){
    Box(
         modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { track.id?.let { id -> navController.navigate("${AppScreens.EigthScreen}/$id") } },
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = track.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = track.duration,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            )

        }
    }
}

