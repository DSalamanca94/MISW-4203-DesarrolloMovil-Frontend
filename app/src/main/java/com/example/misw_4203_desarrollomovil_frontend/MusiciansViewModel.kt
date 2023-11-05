package com.example.misw_4203_desarrollomovil_frontend

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MusiciansViewModel: ViewModel() {
    var _listaMusicians: ArrayList<Musicians> by mutableStateOf(arrayListOf())
    lateinit var _detalleMusician: Musicians

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
                _detalleMusician = response.body()!!
            }
        }
    }
}

