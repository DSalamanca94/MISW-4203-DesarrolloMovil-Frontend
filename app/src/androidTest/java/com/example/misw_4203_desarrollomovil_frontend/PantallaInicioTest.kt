package com.example.misw_4203_desarrollomovil_frontend


import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.misw_4203_desarrollomovil_frontend.screens.HomeScreen
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import com.example.misw_4203_desarrollomovil_frontend.screens.DetalleArtistas

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)

class PantallaInicioTest {

    @get:Rule
    val rule = createComposeRule()
    lateinit var navController: NavController

    @Before
    fun setupAppNavHost() {
        rule.setContent {
            navController = NavController(LocalContext.current)
            HomeScreen(navController = navController)
         }
    }

    @Test
    fun validaInfoMostrada() {
        Thread.sleep(1000)
        //Nombre mostrado aparezca y sea correcto
        rule.onNodeWithText("Artistas").assertExists()

    }
}