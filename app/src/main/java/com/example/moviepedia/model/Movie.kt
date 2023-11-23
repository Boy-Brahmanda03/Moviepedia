package com.example.moviepedia.model

import androidx.annotation.DrawableRes

data class Movie(
    val id: Long,
    val title: String,
    @DrawableRes
    val imageCover: Int,
    val duration: String,
    val releasedYear: Int,
    val rating: Double,
    val director: String,
    val storyline: String,
)
