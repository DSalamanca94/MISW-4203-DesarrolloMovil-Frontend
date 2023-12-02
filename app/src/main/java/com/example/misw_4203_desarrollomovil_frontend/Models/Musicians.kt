package com.example.misw_4203_desarrollomovil_frontend.Models

data class Musicians(
    var id: Int,
    var name: String,
    var image: String,
    var description: String,
    var birthDate: String, // Puedes utilizar un tipo más específico si lo prefieres, como LocalDate.
    var albums: Array<Any>
)

data class Album(
    var id: Int,
    var name: String,
    var cover: String,
    var releaseDate: String, // Puedes utilizar un tipo más específico, como LocalDate, si lo deseas.
    var description: String,
    var genre: String,
    var recordLabel: String,
    var performerPrizes: ArrayList<PerformerPrize>
) {

}

data class PerformerPrize(
    var id: Int,
    var premiationDate: String // Puedes utilizar un tipo más específico si lo prefieres, como LocalDate.
)
