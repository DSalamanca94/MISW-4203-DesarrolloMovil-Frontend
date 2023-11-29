package com.example.misw_4203_desarrollomovil_frontend

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.misw_4203_desarrollomovil_frontend.Views.ListadoAlbumes
import com.example.misw_4203_desarrollomovil_frontend.Models.AlbumList
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListarAlbumTest {

    val albumesArray = arrayListOf(
        AlbumList(
            100,
            "Rubén Blades Bellido de Luna",
            "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            "01-01-1977",
            "Descripcion 100",
            "Salsa",
            "Lable",
            arrayOf(),
            arrayOf(),
            arrayOf()
        ),
        AlbumList(
            101,
            "Rubén Blades Bellido de Luna1",
            "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            "01-01-1977",
            "Descripcion 100",
            "Salsa",
            "Lable",
            arrayOf(),
            arrayOf(),
            arrayOf()
        ),
        AlbumList(
            102,
            "Rubén Blades Bellido de Luna2",
            "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            "01-01-1977",
            "Descripcion 100",
            "Salsa",
            "Lable",
            arrayOf(),
            arrayOf(),
            arrayOf()
        )

    )

    @get:Rule
    val rule = createComposeRule()

    @Before
    fun setupAppNavHost() {
        rule.setContent {
            var navController = NavController(LocalContext.current)
            ListadoAlbumes(navController = navController, listaAbumes = albumesArray)
        }
    }

    @Test
    fun useAppContext() {
        Thread.sleep(5000)
        for (album in albumesArray) {
            rule.onNodeWithText(album.name).assertExists()
            rule.onNodeWithContentDescription(album.cover)
        }
    }

}