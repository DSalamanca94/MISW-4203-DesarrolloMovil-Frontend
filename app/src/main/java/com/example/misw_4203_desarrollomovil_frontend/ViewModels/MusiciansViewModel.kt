package com.example.misw_4203_desarrollomovil_frontend.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.misw_4203_desarrollomovil_frontend.Models.Album
import com.example.misw_4203_desarrollomovil_frontend.Models.Musicians
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.Exception

// Sealed class to represent success or error in network request
sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

class MusiciansViewModel : ViewModel() {

    private var _listaMusicians = MutableLiveData<Result<List<Musicians>>>();
    private var _listaAlbums by mutableStateOf<Result<List<Album>>>(Result.Success(emptyList()));
    val listaMusicians: LiveData<Result<List<Musicians>>> get() = _listaMusicians;
    val listaAlbums: Result<List<Album>> get() = _listaAlbums;

    private var _detalleMusician by mutableStateOf<Result<Musicians>>(
        Result.Success(
            Musicians(
                id = 0,
                name = "",
                image = "",
                description = "",
                birthDate = "",
                albums = emptyArray()
            )
        )
    )
    val detalleMusician: Result<Musicians> get() = _detalleMusician

    // Function to fetch musicians from the API
    fun getMusicians() {
        viewModelScope.launch {
            try {
                // Call the suspended function to get musicians and handle the result
                val response = withContext(Dispatchers.IO) {
                    RetroficClient.webService.getMusicians()
                }

                if (response.isSuccessful) {
                    val musiciansList = response.body() ?: emptyList()
                    _listaMusicians.postValue(Result.Success(musiciansList))
                } else {
                    _listaMusicians.postValue(Result.Error(Exception("Error in the request: ${response.code()}")))
                }
            } catch (e: Exception) {
                _listaMusicians.postValue(Result.Error(e))
            }
        }
    }

    // Suspended function to get a musician by ID from the API
    fun getMusiciansById(musicianId: String) {
        viewModelScope.launch {
            try {
                // Call the suspended function to get a musician by ID and handle the result
                val response = withContext(Dispatchers.IO) {
                    RetroficClient.webService.getMusiciansbyId(musicianId)
                }

                if (response.isSuccessful) {
                    val musician = response.body()
                        ?: Musicians(
                            id = 0,
                            name = "",
                            image = "",
                            description = "",
                            birthDate = "",
                            albums = emptyArray()
                        )
                    _detalleMusician = Result.Success(musician)
                } else {
                    _detalleMusician =
                        Result.Error(Exception("Error in the request: ${response.code()}"))
                }
            } catch (e: Exception) {
                _detalleMusician = Result.Error(e)
            }
        }
    }

    // Function to fetch albums by musician ID from API
    fun getAlbumsbyMusicianId(musicianId: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetroficClient.webService.getAlbumsbyMusicianId(musicianId);
                }

                if (response.isSuccessful) {
                    val albumsList = response.body()?: emptyList<Any>();
                    _listaAlbums = Result.Success(albumsList as List<Album>);

                    println("_listaAlbums: ${_listaAlbums}");

                    // Print Result
                    albumsList.forEach { album ->
                        println("Album ID: ${album.id}, Name: ${album.name}")
                    }
                } else {
                    _listaAlbums = Result.Error(Exception("Error in the request: ${response.code()}"));
                }

            } catch (e: Exception) {
                _listaAlbums = Result.Error(e);
            }
        }
    }
}