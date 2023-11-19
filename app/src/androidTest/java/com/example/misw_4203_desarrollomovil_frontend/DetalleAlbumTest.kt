package com.example.misw_4203_desarrollomovil_frontend


import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.misw_4203_desarrollomovil_frontend.screens.DetalleAlbum
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetalleAlbumTest {
    val album = AlbumList(
        100,
        "Buscando América",
        "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
        "984-08-01T05:00:00.000Z",
        "Buscando América es el primer álbum de la banda de Rubén Blades y Seis del Solar lanzado en 1984. La producción, bajo el sello Elektra, fusiona diferentes ritmos musicales tales como la salsa, reggae, rock, y el jazz latino. El disco fue grabado en Eurosound Studios en Nueva York entre mayo y agosto de 1983.",
        "Salsa",
        "Elektra",
        arrayOf(),
        arrayOf(),
        arrayOf()
        )

    @get:Rule
    val rule = createComposeRule()

    @Before
    fun setupAppNavHost() {
        rule.setContent {
            DetalleAlbum(album = album)
        }
    }

    @Test
    fun verifyArtistDetails() {
        Thread.sleep(5000)
        rule.onNodeWithContentDescription(album.cover)
        rule.onNodeWithText(album.name).assertExists()
        rule.onNodeWithText(album.releaseDate).assertExists()
        rule.onNodeWithText(album.description).assertExists()
        rule.onNodeWithText(album.genre).assertExists()
        rule.onNodeWithText(album.recordLabel).assertExists()
    }
}