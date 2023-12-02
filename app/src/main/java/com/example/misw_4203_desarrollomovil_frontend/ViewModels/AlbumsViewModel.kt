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

    //Cache
    private var albumsCache: ArrayList<AlbumList>? = null;
    private var albumIdCache: AlbumList? = null;
    private var tracksCache: ArrayList<TrackList>? = null;

    fun getAlbumes() {
        viewModelScope.launch {
            // Verificar si la respuesta está en caché
            if (albumsCache != null) {
                _listaAlbumes = albumsCache!!
            } else {
                try {
                    // La respuesta no está en caché, realizar la solicitud a la red
                    val response = RetroficClient.webService.getAlbumes()

                    if (response.isSuccessful) {
                        val albumes = response.body() ?: emptyList<AlbumList>()
                        _listaAlbumes = ArrayList(albumes)

                        // Almacenar el resultado en caché
                        albumsCache = ArrayList(albumes)
                    } else {
                        // Manejar errores de respuesta no exitosa (códigos HTTP diferentes a 200)
                        showErrorInView("Error: ${response.code()}")
                    }
                } catch (e: Exception) {
                    // Manejar errores de red u otros errores
                    showErrorInView("Error: ${e.message}")
                }
            }
        }
    }

    fun GetAlbumbyId(Album_Id: String) {
        viewModelScope.launch {
            // Verificar si el álbum está en caché
            if (albumIdCache != null) {
                _detalleAlbum = albumIdCache!!
            } else {
                try {
                    val response = RetroficClient.webService.getAlbumbyId(Album_Id);

                    if (response.isSuccessful) {
                        // Álbum encontrado en la red, asignar a _detalleAlbum y almacenar en caché
                        val album = response.body() ?: AlbumList(
                            id = 0,
                            name = "",
                            cover = "",
                            releaseDate = "",
                            description = "",
                            genre = "",
                            recordLabel = "",
                            tracks = emptyArray(),
                            performers = emptyArray(),
                            comments = emptyArray()
                        );
                        _detalleAlbum = album;
                        albumIdCache = album;
                    } else {
                        // Manejar errores de respuesta no exitosa (códigos HTTP diferentes a 200)
                        showErrorInView("Error: ${response.code()}");
                    }
                } catch (e: Exception) {
                    // Manejar errores de red u otros errores
                    showErrorInView("Error: ${e.message}");
                }
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
            // Verificar si las pistas están en caché
            if (tracksCache != null) {
                _listaTracks = tracksCache!!;
            } else {
                try {
                    val response = RetroficClient.webService.getTracks(Album_Id);

                    if (response.isSuccessful) {
                        // Pistas encontradas en la red, asignar a _listaTracks y almacenar en caché
                        val tracks = response.body() as? ArrayList<TrackList>;
                        tracks?.let {
                            _listaTracks = it;
                            tracksCache = ArrayList(it);
                        } ?: run {
                            showErrorInView("Error: Lista de pistas vacía");
                        }
                    } else {
                        // Manejar errores de respuesta no exitosa (códigos HTTP diferentes a 200)
                        showErrorInView("Error: ${response.code()}");
                    }
                } catch (e: Exception) {
                    // Manejar errores de red u otros errores
                    showErrorInView("Error: ${e.message}");
                }
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