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
        Musicians(
            100,
            "Queen",
            "https://pm1.narvii.com/6724/a8b29909071e9d08517b40c748b6689649372852v2_hq.jpg",
            "Descripción del músico",
            "01-01-1977",
            arrayOf()
        ),
        Musicians(
            101,
            "Queen1",
            "https://pm1.narvii.com/6724/a8b29909071e9d08517b40c748b6689649372852v2_hq.jpg",
            "Descripción del músico2",
            "01-01-1980",
            arrayOf()
        ),
        Musicians(
            102,
            "Queen2",
            "https://pm1.narvii.com/6724/a8b29909071e9d08517b40c748b6689649372852v2_hq.jpg",
            "Descripción del músico3",
            "01-01-1985",
            arrayOf()
        )

    )

    @get:Rule
    val rule = createComposeRule()

    @Before
    fun setupAppNavHost() {
        rule.setContent {
            var navController = NavController(LocalContext.current)
            ListadoArtistas(navController = navController, listaMusicians = musiciansArray)
        }
    }

    @Test
    fun useAppContext() {
        Thread.sleep(5000)
        for (musician in musiciansArray) {
            rule.onNodeWithText(musician.name).assertExists()
            rule.onNodeWithContentDescription(musician.image)
        }
    }

}