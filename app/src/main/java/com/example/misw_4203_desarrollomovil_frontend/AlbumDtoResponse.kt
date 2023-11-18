package com.example.misw_4203_desarrollomovil_frontend

data class AlbumDtoResponse(
    var id: Int,
    var name: String,
    var cover: String,
    var releaseDate: String,
    var description: String,
    var genre: String,
    var recordLabel: String,
)
