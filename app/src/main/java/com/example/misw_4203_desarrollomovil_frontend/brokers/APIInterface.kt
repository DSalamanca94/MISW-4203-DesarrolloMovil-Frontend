package com.example.misw_4203_desarrollomovil_frontend.brokers

import Artist
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIInterface {
    @GET
    suspend fun getMusicians(@Url url:String): Response<List<Artist>>
}