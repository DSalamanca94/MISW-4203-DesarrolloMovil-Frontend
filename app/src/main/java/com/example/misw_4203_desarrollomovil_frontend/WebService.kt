package com.example.misw_4203_desarrollomovil_frontend
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.POST

interface WebService {
    @GET("/musicians")
    suspend fun getMusicians(): Response<List<Musicians>>

    @GET("/musicians/{id}")
    suspend fun getMusiciansbyId(
        @Path("id") musicianId: String
    ): Response<Musicians>

    @GET("/albums")
    suspend fun getAlbumes(): Response<List<AlbumList>>

    @GET("/albums/{id}")
    suspend fun getAlbumbyId(
        @Path("id") albumId: String
    ): Response<AlbumList>

    @POST("/albums")
    suspend fun addAlbum(
        @Body album: AlbumDto
    ): Response<AlbumDtoResponse>

}