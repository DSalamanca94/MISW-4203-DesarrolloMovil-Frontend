package com.example.misw_4203_desarrollomovil_frontend
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    @GET("/musicians")
    suspend fun getMusicians(): Response<List<Musicians>>

    @GET("/musicians/{id}")
    suspend fun getMusiciansbyId(
        @Path("id") musicianId: String
    ): Response<Musicians>
}