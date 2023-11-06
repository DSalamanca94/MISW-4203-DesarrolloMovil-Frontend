package com.example.misw_4203_desarrollomovil_frontend

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MusiciansViewModel: ViewModel() {
    var _listaMusicians: ArrayList<Musicians> by mutableStateOf(arrayListOf())
    var _detalleMusician: Musicians = Musicians(
        id = 0,
        name ="",
        image = "",
        description = "",
        birthDate = "",
        albums = emptyArray())

    fun GetMusicians() {
        viewModelScope.launch(Dispatchers.IO){
            val response = RetroficClient.webService.getMusicians()
            withContext(Dispatchers.Main){
                _listaMusicians = (response.body() as ArrayList<Musicians>?)!!
            }
        }
    }

    fun GetMusiciansbyId(Musician_Id: String) {
        viewModelScope.launch(Dispatchers.IO){
            val response = RetroficClient.webService.getMusiciansbyId(Musician_Id)
            withContext(Dispatchers.Main){
                _detalleMusician = response.body()?:Musicians(
                    id = 0,
                    name ="",
                    image = "",
                    description = "",
                    birthDate = "",
                    albums = emptyArray())
            }
        }
    }
}

