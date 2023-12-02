package com.example.misw_4203_desarrollomovil_frontend.ViewModels

import android.view.View
import android.widget.TextView
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.misw_4203_desarrollomovil_frontend.Models.AlbumDto
import com.example.misw_4203_desarrollomovil_frontend.Models.AlbumList
import com.example.misw_4203_desarrollomovil_frontend.Models.Collector
import com.example.misw_4203_desarrollomovil_frontend.Models.Comment
import com.example.misw_4203_desarrollomovil_frontend.Models.TrackList
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

open class AlbumsViewModel : ViewModel(){
    var _listaAlbumes: ArrayList<AlbumList> by mutableStateOf(arrayListOf())
    var _listaTracks: ArrayList<TrackList> by mutableStateOf(arrayListOf())
    var _listaCollectors: ArrayList<Collector> by mutableStateOf(arrayListOf())

    var _detalleAlbum: AlbumList = AlbumList(
        id = 0,
        name ="",
        cover = "",
        releaseDate = "",
        description = "",
        genre= "",
        recordLabel= "",
        tracks = emptyArray(),
        performers= emptyArray(),
        comments = emptyArray())

    fun getAlbumes() {
        viewModelScope.launch {
            try {
                val response = RetroficClient.webService.getAlbumes()

                if (response.isSuccessful) {
                    val albumes = response.body() as? ArrayList<AlbumList>
                    albumes?.let {
                        withContext(Dispatchers.Main) {
                            _listaAlbumes = it
                        }
                    } ?: run {
                        showErrorInView("Error: Lista vacía")
                    }
                } else {
                    // Manejar errores de respuesta no exitosa (códigos HTTP diferentes a 200)
                    showErrorInView("Error: ${response.code()}")
                }
            } catch (e: Exception) {
                showErrorInView("Error: ${e.message}")
            }
        }
    }

    fun GetAlbumbyId(Album_Id: String) {
        viewModelScope.launch {
            val response = RetroficClient.webService.getAlbumbyId(Album_Id)
            withContext(Dispatchers.Main){
                _detalleAlbum = response.body()?: AlbumList(
                    id = 0,
                    name ="",
                    cover = "",
                    releaseDate = "",
                    description = "",
                    genre= "",
                    recordLabel= "",
                    tracks = emptyArray(),
                    performers= emptyArray(),
                    comments = emptyArray()
                )

            }
        }
    }

    fun addAlbum(album: AlbumDto) {
        viewModelScope.launch {
            val response = RetroficClient.webService.addAlbum(album)
            withContext(Dispatchers.Main) {
                println("Codigo: $response.code()")
                if(response.code() == 200){
                    println("Entro: $response.code()")
                } else {
                    println("Error: ${response.code()}")
                    val errorBody = response.errorBody()?.string()
                    println("Error Details: $errorBody")
                }
            }
        }
    }

    fun getTracks(Album_Id: String) {
        viewModelScope.launch {
            try {
                val response = RetroficClient.webService.getTracks(Album_Id)

                if (response.isSuccessful) {
                    val tracks = response.body() as? ArrayList<TrackList>
                    tracks?.let {
                        withContext(Dispatchers.Main) {
                            _listaTracks = it
                        }
                    } ?: run {
                        showErrorInView("Error: Lista vacía")
                    }
                } else {
                    // Manejar errores de respuesta no exitosa (códigos HTTP diferentes a 200)
                    showErrorInView("Error: ${response.code()}")
                }
            } catch (e: Exception) {
                showErrorInView("Error: ${e.message}")
            }
        }
    }

    fun addComment(albumId: String, comment: Comment) {
        viewModelScope.launch {
            val response = RetroficClient.webService.addComment(albumId, comment)
            withContext(Dispatchers.Main) {
                println("Codigo: $response.code()")
                if(response.code() == 200){
                    println("Entro: $response.code()")
                } else {
                    println("Error: ${response.code()}")
                    val errorBody = response.errorBody()?.string()
                    println("Error Details: $errorBody")
                }
            }
        }
    }

    suspend fun getCollectors(): List<Collector> {
        try {
            val response = RetroficClient.webService.getCollectors()

            if (response.isSuccessful) {
                val collectors = response.body() as? List<Collector>
                collectors?.let {
                    return it
                } ?: throw Exception("Lista vacía")
            } else {
                throw Exception("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            throw Exception("Error: ${e.message}")
        }
    }


    fun addAlbumToMusician(album: String, musician: String) {
        viewModelScope.launch {
            val response = RetroficClient.webService.addAlbumToMusician(musician, album)
            withContext(Dispatchers.Main) {
                println("Codigo Add Album: ${response.code()}");
                println("Codigo Add Album: $response");
                if(response.code() == 200){
                    println("Entro: $response")
                } else {
                    println("Error: ${response.code()}")
                    val errorBody = response.errorBody()?.string()
                    println("Error Details: $errorBody")
                }
            }
        }
    }

    private lateinit var errorTextView: TextView
     private fun showErrorInView(message: String) {
        errorTextView.text = message
        errorTextView.visibility = View.VISIBLE
    }

}