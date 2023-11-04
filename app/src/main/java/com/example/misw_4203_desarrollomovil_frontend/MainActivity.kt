package com.example.misw_4203_desarrollomovil_frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.foundation.layout.Box
import androidx.navigation.compose.rememberNavController
import com.example.misw_4203_desarrollomovil_frontend.navigation.AppNavigation
import com.example.misw_4203_desarrollomovil_frontend.screens.HomeScreen
import com.example.misw_4203_desarrollomovil_frontend.screens.ListadoArtistas

class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MusiciansViewModel> ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MISW4203DesarrolloMovilFrontendTheme {
                Surface {
                    /*AppNavigation()*/
                    val navController = rememberNavController()

                    Box(
                        modifier = Modifier.fillMaxSize()
                    ){
                        viewModel.GetMusicians()
                        ListadoArtistas(viewModel._listaMusicians , viewModel ,navController)
                    }
                }
            }
        }
    }
}

