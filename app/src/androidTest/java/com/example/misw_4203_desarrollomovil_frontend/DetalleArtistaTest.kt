package com.example.misw_4203_desarrollomovil_frontend


import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.navigation.NavController
import com.example.misw_4203_desarrollomovil_frontend.Models.Album
import com.example.misw_4203_desarrollomovil_frontend.Models.AlbumList
import com.example.misw_4203_desarrollomovil_frontend.Views.DetalleArtistas
import com.example.misw_4203_desarrollomovil_frontend.Models.Musicians
import com.example.misw_4203_desarrollomovil_frontend.ViewModels.AlbumsViewModel
import com.example.misw_4203_desarrollomovil_frontend.ViewModels.Result
import com.example.misw_4203_desarrollomovil_frontend.Views.DetalleArtistasContent

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

class DetalleArtistasContentTest {

    @get:Rule
    val composeTestRule  = createComposeRule()


    @Test
    fun testDetalleArtistasContent() {

        composeTestRule.launchActivity(intent)

        val musician = Musicians(
            100,
            "Queen",
            "https://pm1.narvii.com/6724/a8b29909071e9d08517b40c748b6689649372852v2_hq.jpg",
            "Descripción del músico",
            "01-01-2023",
            arrayOf(1)
        )
        val result = Result.Success(musician)

        val albumPoetaDelPueblo = AlbumList(
            id = 101,
            name = "Poeta del pueblo",
            cover = "https://cdn.shopify.com/s/files/1/0275/3095/products/image_4931268b-7acf-4702-9c55-b2b3a03ed999_1024x1024.jpg",
            releaseDate = "1984-08-01T00:00:00.000Z",
            description = "Recopilación de 27 composiciones del cosmos Blades que los bailadores y melómanos han hecho suyas en estos 40 años de presencia de los ritmos y concordias afrocaribeños en múltiples escenarios internacionales. Grabaciones de Blades para la Fania con las orquestas de Pete Rodríguez, Ray Barreto, Fania All Stars y, sobre todo, los grandes éxitos con la Banda de Willie Colón",
            genre = "Salsa",
            recordLabel = "Elektra",
            tracks = emptyArray(),
            performers = emptyArray(),
            comments = emptyArray()
        )
        val listaAlbumes = arrayListOf(albumPoetaDelPueblo)

        val result_album: Result<List<Album>> = Result.Success(listaAlbumes.map { albumList ->
            Album(
                id = albumList.id,
                name = albumList.name,
                cover = albumList.cover,
                releaseDate = albumList.releaseDate,
                description = albumList.description,
                genre = albumList.genre,
                recordLabel = albumList.recordLabel,
                performerPrizes = ArrayList(),
            )
        })

        composeTestRule.setContent {
             // (musician: Result<Musicians>, listaAlbumsResult: Result<List<Album>>, listaAlbumes: ArrayList<AlbumList>, viewModelA: AlbumsViewModel,  modifier: Modifier) {
          DetalleArtistasContent(musician = result,listaAlbumsResult = result_album, listaAlbumes = listaAlbumes ,viewModelA = AlbumsViewModel(), modifier = Modifier)
        }

        // Verificar la presencia y contenido de los elementos en el formulario
        composeTestRule.onNodeWithText("Description").assertIsDisplayed()
        composeTestRule.onNodeWithText("Descripción del músico").assertIsDisplayed()
        // Puedes agregar más verificaciones según los elementos y su contenido esperado
    }
}