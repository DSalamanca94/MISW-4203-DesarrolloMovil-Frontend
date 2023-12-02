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

    // Cache
    private val getMusiciansCache: MutableMap<Unit, Result<List<Musicians>>> = HashMap();
    private val getMusicianByIdCache: MutableMap<String, Result<Musicians>> = HashMap();
    private val getAlbumsByMusicianIdCache: MutableMap<String, Result<List<Album>>> = HashMap();

    // Function to fetch musicians from the API
    fun getMusicians() {
        viewModelScope.launch {
            val cacheKey = Unit;

            //Verify response in cache
            if (getMusiciansCache.containsKey(cacheKey)) {
                val cacheResult = getMusiciansCache[cacheKey]!!;
                _listaMusicians.postValue(cacheResult);
            } else {
                try {
                    // If response not in cache, go with API
                    val response = withContext(Dispatchers.IO) {
                        RetroficClient.webService.getMusicians();
                    }

                    if (response.isSuccessful) {
                        val musiciansList = response.body() ?: emptyList();
                        val result = Result.Success(musiciansList);
                        _listaMusicians.postValue((result));

                        //Store cache result
                        getMusiciansCache[cacheKey] = result;
                    }
                } catch (e: Exception) {
                    val errorResult = Result.Error(e);
                    _listaMusicians.postValue(errorResult);

                    // Store error result in cache
                    getMusiciansCache[cacheKey] = errorResult;
                }
            }
        }
    }

    // Suspended function to get a musician by ID from the API
    fun getMusiciansById(musicianId: String) {
        viewModelScope.launch {
            // Verify Data in cache
            if (getMusicianByIdCache.containsKey(musicianId)) {
                val cachedResult = getMusicianByIdCache[musicianId]!!
                _detalleMusician = cachedResult
            } else {
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
                        val result = Result.Success(musician);
                        _detalleMusician = result;

                        // Store result in cache
                        getMusicianByIdCache[musicianId] = result;
                    } else {
                        val errorResult = Result.Error(Exception("Error in the request: ${response.code()}"));
                        _detalleMusician = errorResult;
                    }
                } catch (e: Exception) {
                    val errorResult = Result.Error(e);
                    _detalleMusician = errorResult;

                    //Store result in cache
                    getMusicianByIdCache[musicianId] = errorResult;
                }
            }
        }
    }

    // Function to fetch albums by musician ID from API
    fun getAlbumsbyMusicianId(musicianId: String) {
        viewModelScope.launch {
            //Verify response in cache
            if (getAlbumsByMusicianIdCache.containsKey(musicianId)) {
                val cachedResult = getAlbumsByMusicianIdCache[musicianId]!!
                _listaAlbums = cachedResult
            } else {
                try {
                    // If response not in cache API
                    val response = withContext(Dispatchers.IO) {
                        RetroficClient.webService.getAlbumsbyMusicianId(musicianId);
                    }

                    if (response.isSuccessful) {
                        val albumsList = response.body()?: emptyList<Any>();
                        val response = Result.Success(albumsList as List<Album>);
                        _listaAlbums = response;

                        //Store result in cache
                        getAlbumsByMusicianIdCache[musicianId] = response;

                        // Print Result
                        albumsList.forEach { album ->
                            println("Album ID: ${album.id}, Name: ${album.name}")
                        }
                    } else {
                        val errorResponse = Result.Error(Exception("Error in the request: ${response.code()}"));
                        _listaAlbums = errorResponse;

                        //Store result in cache
                        getAlbumsByMusicianIdCache[musicianId] = errorResponse;
                    }
                } catch (e: Exception) {
                    val errorResult = Result.Error(e);
                    _listaAlbums = errorResult;

                    //Store result
                    getAlbumsByMusicianIdCache[musicianId] = errorResult;
                }
            }
        }
    }
}