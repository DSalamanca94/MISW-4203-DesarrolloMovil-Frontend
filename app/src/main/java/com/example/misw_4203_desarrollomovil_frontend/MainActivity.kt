package com.example.misw_4203_desarrollomovil_frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.example.misw_4203_desarrollomovil_frontend.ViewModels.AlbumsViewModel
import com.example.misw_4203_desarrollomovil_frontend.ViewModels.MusiciansViewModel
import com.example.misw_4203_desarrollomovil_frontend.ui.theme.MISW4203DesarrolloMovilFrontendTheme
import com.example.misw_4203_desarrollomovil_frontend.ViewModels.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    val viewModelA by viewModels<AlbumsViewModel> ()
    val viewModel by viewModels<MusiciansViewModel> ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MISW4203DesarrolloMovilFrontendTheme {
                Surface {
                    AppNavigation(viewModel,viewModelA)
                }
            }
        }
    }
}


