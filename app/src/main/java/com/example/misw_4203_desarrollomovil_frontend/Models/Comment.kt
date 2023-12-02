package com.example.misw_4203_desarrollomovil_frontend.Models

data class Comment(
    var description: String,
    var rating: Int,
    val collector: Collector
)

data class Collector(
    var id: Int,
    var name: String,
    var telephone: String,
    var email: String,
)

