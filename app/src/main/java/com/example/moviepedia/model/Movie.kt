package com.example.moviepedia.model

data class Movie(
    val id: Long,
    val title: String,
    val image: Int,
    val duration: String,
    val releasedYear: Int,
    val rating: Double,
    val director: String,
    val storyline: String,
)
