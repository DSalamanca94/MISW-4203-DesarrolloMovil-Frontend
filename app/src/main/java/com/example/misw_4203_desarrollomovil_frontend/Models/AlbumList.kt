package com.example.misw_4203_desarrollomovil_frontend.Models

data class AlbumList(
    var id: Int,
    var name: String,
    var cover: String,
    var releaseDate: String,
    var description: String,
    var genre: String,
    var recordLabel: String,
    var tracks: Array<Any>,
    var performers: Array<Any>,
    var comments : Array<Any>,

)