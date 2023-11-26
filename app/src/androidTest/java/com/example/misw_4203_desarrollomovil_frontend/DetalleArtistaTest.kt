package com.example.misw_4203_desarrollomovil_frontend


import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.navigation.NavController
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

class DetalleArtistaTest {

    val musician = Musicians(100, "Queen", "https://pm1.narvii.com/6724/a8b29909071e9d08517b40c748b6689649372852v2_hq.jpg","Descripción del músico", "01-01-2023",
        arrayOf(1))

    @get:Rule
    val rule = createComposeRule()
    lateinit var navController: NavController

    /*@Before
    fun setupAppNavHost() {
        rule.setContent {
            navController = NavController(LocalContext.current)
            DetalleArtistas(navController = navController, musician = musician )
            //HomeScreen(navController = navController)
         }
    }*/

    @Test
    fun validaInfoMostrada() {
        Thread.sleep(5000)
        rule.onNodeWithText(musician.name).assertExists()
        rule.onNodeWithText(musician.description).assertExists()
        rule.onNodeWithContentDescription(musician.image)

    }
}