package com.example.misw_4203_desarrollomovil_frontend

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.misw_4203_desarrollomovil_frontend.Models.TrackList
import com.example.misw_4203_desarrollomovil_frontend.Views.ListadoTracks
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListarTracksTest {

    val tracksArray = arrayListOf(
        TrackList(
            200,
            "Track 1",
            "40"
        ),
        TrackList(
            201,
            "Track 2",
            "30"
        ),
        TrackList(
            202,
            "Track 3",
            "20",
        )
    )

    @get:Rule
    val rule = createComposeRule()

    @Before
    fun setupAppNavHost() {
        rule.setContent {
            var navController = NavController(LocalContext.current)
            ListadoTracks(navController = navController, listaTracks = tracksArray)
        }
    }

    @Test
    fun testListarTracks() {
        Thread.sleep(5000)
        for (track in tracksArray) {
            rule.onNodeWithText(track.name).assertExists()
            // Puedes agregar más assertions según sea necesario para otros elementos en el componente de lista de tracks
        }
    }
}
