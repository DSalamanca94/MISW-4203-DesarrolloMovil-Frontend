package com.example.misw_4203_desarrollomovil_frontend


import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.navigation.NavController
import com.example.misw_4203_desarrollomovil_frontend.Views.DetalleArtistas
import com.example.misw_4203_desarrollomovil_frontend.Models.Musicians
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
    val composeTestRule = createComposeRule()

    @Test
    fun testDetalleArtistasContent() {
        val musician = Musicians(
            100,
            "Queen",
            "https://pm1.narvii.com/6724/a8b29909071e9d08517b40c748b6689649372852v2_hq.jpg",
            "Descripción del músico",
            "01-01-2023",
            arrayOf(1)
        )
        val result = Result.Success(musician)

        composeTestRule.setContent {
          //  DetalleArtistasContent(musician = result, modifier = Modifier)
        }

        // Verificar la presencia y contenido de los elementos en el formulario
        composeTestRule.onNodeWithText("Description").assertIsDisplayed()
        composeTestRule.onNodeWithText("Descripción del músico").assertIsDisplayed()
        // Puedes agregar más verificaciones según los elementos y su contenido esperado
    }
}