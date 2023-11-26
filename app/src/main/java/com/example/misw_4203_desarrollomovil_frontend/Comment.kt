package com.example.misw_4203_desarrollomovil_frontend

data class Comment(
    var description: String,
    var rating: Int,
    val collector: Collector
)

data class Collector(
    val id: Int
)

