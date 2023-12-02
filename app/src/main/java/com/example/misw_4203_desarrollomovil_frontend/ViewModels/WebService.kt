package com.example.misw_4203_desarrollomovil_frontend.ViewModels
import com.example.misw_4203_desarrollomovil_frontend.Models.Album
import com.example.misw_4203_desarrollomovil_frontend.Models.AlbumDto
import com.example.misw_4203_desarrollomovil_frontend.Models.AlbumDtoResponse
import com.example.misw_4203_desarrollomovil_frontend.Models.AlbumList
import com.example.misw_4203_desarrollomovil_frontend.Models.Comment
import com.example.misw_4203_desarrollomovil_frontend.Models.Musicians
import com.example.misw_4203_desarrollomovil_frontend.Models.TrackList
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

    @GET("/albums/{id}/tracks")
    suspend fun getTracks(
        @Path("id") albumId: String
    ): Response<List<TrackList>>

    @POST("/albums/{id}/comments")
    suspend fun addAComment(
        @Path("id") albumId: String,
        @Body comment: Comment
    ): Response<Comment>

    @GET("/musicians/{id}/albums")
    suspend fun getAlbumsbyMusicianId(
        @Path("id") musicianId: String
    ): Response<List<Album>>
    @POST("/musicians/{musicianId}/albums/{albumId}")
    suspend fun addAlbumToMusician(
        @Path("musicianId") musicianId: String,
        @Path("albumId") albumId: String
    ): Response<AlbumDtoResponse>

}