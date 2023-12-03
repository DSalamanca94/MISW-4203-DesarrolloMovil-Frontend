package com.example.misw_4203_desarrollomovil_frontend

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.misw_4203_desarrollomovil_frontend.Models.Album
import com.example.misw_4203_desarrollomovil_frontend.Models.AlbumList
import com.example.misw_4203_desarrollomovil_frontend.Models.Musicians
import com.example.misw_4203_desarrollomovil_frontend.Models.PerformerPrize
// import com.example.misw_4203_desarrollomovil_frontend.Models.Result
import com.example.misw_4203_desarrollomovil_frontend.ViewModels.AlbumsViewModel
import com.example.misw_4203_desarrollomovil_frontend.ViewModels.Result
import com.example.misw_4203_desarrollomovil_frontend.Views.DetalleArtistas
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AssociateAlbumTest {

    val artist = Musicians(
        1,
        "Artista de Prueba",
        "https://example.com/artist.jpg",
        "Descripción del artista",
        "Genre",
        emptyArray()
    )

    val albumsArray = listOf(
        Album(1, "Álbum 1", "https://example.com/album1.jpg", "Descripción 1", "Pop", "Label 1", "", arrayListOf(PerformerPrize(id = 1, premiationDate = "01-01-2023"))),
        Album(2, "Álbum 2", "https://example.com/album2.jpg", "Descripción 2", "Rock", "Label 2","",arrayListOf(PerformerPrize(id = 1, premiationDate = "01-01-2023"))),
        Album(3, "Álbum 3", "https://example.com/album3.jpg", "Descripción 3", "Hip Hop", "", "",arrayListOf(PerformerPrize(id = 1, premiationDate = "01-01-2023")))
    )

    @get:Rule
    val rule = createComposeRule()


    // @Before
    fun setupAppNavHost() {
        //rule.setContent {
        //    val navController = NavController(LocalContext.current)
        //    val albumList = albumsArray.map {
        //        AlbumList(
         //           id = it.id,
         //           name = it.name,
          //          cover = it.cover,
          //          releaseDate = it.releaseDate,
            //        description = it.description,
            //        genre = it.genre,
            //        recordLabel = it.recordLabel,
            //        performerPrizes = ArrayList() // You might need to provide appropriate data here
            //    )
            //}
            // DetalleArtistas(
            //    navController = navController,
            //    musician = Result.Success(artist),
            //    listaAlbums = Result.Success(albumList),
            //    listaAlbumes = ArrayList(albumsArray),
            //    viewModelA = AlbumsViewModel()
            //)
        //}
    }
    //@Test
    fun testAssociateAlbum() {
        // Asegurar que la pantalla de detalle del artista esté visible
        rule.onNodeWithText(artist.name).assertExists()

        // Hacer clic en el botón para agregar un álbum
        rule.onNodeWithContentDescription("Agregar un Álbum").performClick()

        // Seleccionar un álbum en el cuadro de diálogo (puede variar según tu implementación)
        // Aquí, por ejemplo, seleccionamos el primer álbum de la lista
        rule.onNodeWithText(albumsArray[0].name).performClick()

        // Confirmar el cuadro de diálogo
        rule.onNodeWithText("Confirmar").performClick()

        // Asegurar que la acción se ha realizado correctamente
        // Puedes realizar assertions adicionales según la lógica de tu aplicación
        rule.onNodeWithText("Álbum agregado correctamente").assertExists()
    }
}
