package com.example.misw_4203_desarrollomovil_frontend

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.navigation.NavController
import com.example.misw_4203_desarrollomovil_frontend.screens.ListadoArtistas

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
@RunWith(AndroidJUnit4::class)
class ListarArtistaTest {

    val musiciansArray = arrayListOf(
        Musicians(100, "Queen", "https://pm1.narvii.com/6724/a8b29909071e9d08517b40c748b6689649372852v2_hq.jpg","Descripción del músico", "01-01-202",
            arrayListOf()
        ),

    )

    @get:Rule
    val rule = createComposeRule()
    lateinit var navController: NavController

    @Before
    fun setupAppNavHost() {
        rule.setContent {
            navController = NavController(LocalContext.current)
            ListadoArtistas(navController = navController, listaMusicians = musiciansArray)
        }
    }

    @Test
    fun useAppContext() {
        Thread.sleep(5000)
        for (musician in musiciansArray) {
            rule.onNodeWithContentDescription(musician.image)
            rule.onNodeWithText(musician.name).assertExists()
        }
    }
}