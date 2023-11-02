package com.example.misw_4203_desarrollomovil_frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.misw_4203_desarrollomovil_frontend.ui.theme.MISW4203DesarrolloMovilFrontendTheme
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MISW4203DesarrolloMovilFrontendTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally, // Centra el contenido horizontalmente
                ) {
                    ArtistButton()
                }

            }
        }
    }
}
@Composable
fun ArtistButton() {
    Button(
        onClick = {
            // Aquí puedes definir el comportamiento que deseas cuando se haga clic en el botón "Artista".
            // Por ejemplo, puedes navegar a otra pantalla o realizar alguna acción específica.
        },
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = "Artistas")
    }
}
