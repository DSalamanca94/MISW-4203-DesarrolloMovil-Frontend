package com.example.misw_4203_desarrollomovil_frontend.Albumns.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.misw_4203_desarrollomovil_frontend.R
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlbumsScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()

        ){
            BotonTest()
            Encabezado()
        }
    }
}

@Composable
fun BotonTest() {
    Image(
        painter = painterResource(id = R.drawable.back_icon),
        contentDescription = null,
        modifier = Modifier
            .size(52.dp)
    )

}


@Composable
fun Encabezado() {

    Text(
        text = "Albums",
        fontSize = 42.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
    )
}