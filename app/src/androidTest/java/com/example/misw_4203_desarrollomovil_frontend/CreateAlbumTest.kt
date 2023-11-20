package com.example.misw_4203_desarrollomovil_frontend

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.misw_4203_desarrollomovil_frontend.screens.Formulario
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before


@RunWith(AndroidJUnit4::class)
class CreateAlbumTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: AlbumsViewModel

    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

    @Before
    fun setUp() {
        viewModel = AlbumsViewModel() // Crear manualmente una instancia del ViewModel

        composeTestRule.setContent {
            val name = remember { mutableStateOf("") }
            val cover = remember { mutableStateOf("") }
            val releaseDate = remember { mutableStateOf("") }
            val description = remember { mutableStateOf("") }
            val genre = remember { mutableStateOf("") }
            val recordLabel = remember { mutableStateOf("") }

            Formulario(
                navController = navController,
                viewModelA = viewModel
            )
        }
    }

    @Test
    fun testCreateAlbum() {
        Thread.sleep(5000)
        val albumDto = AlbumDto(
            name = "Nevermind",
            cover = "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            releaseDate = "9184-08-01",
            description = "uscando América es el primer álbum de la banda de Rubén Blades y Seis del Solar lanzado en 1984. La producción, bajo el sello Elektra, fusiona diferentes ritmos musicales tales como la salsa, reggae, rock, y el jazz latino. El ",
            genre = "Rock",
            recordLabel = "Elektra"
        )
        viewModel.addAlbum(albumDto)
    }
}