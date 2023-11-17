package com.example.moviepedia.data

import com.example.moviepedia.model.MovieDataSource
import com.example.moviepedia.model.UserMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MoviesRepository {

    val userMovies = mutableListOf<UserMovies>()

    init {
        if (userMovies.isEmpty()) {
            MovieDataSource.movieList.forEach {
                userMovies.add(UserMovies(it, isOnWatchList = false, isFavorited = false))
            }
        }
    }

    fun getAllMovies(): Flow<List<UserMovies>> = flowOf(userMovies)

    companion object {
        @Volatile
        private var instance: MoviesRepository? = null

        fun getInstance(): MoviesRepository =
            instance ?: synchronized(this) {
                MoviesRepository().apply {
                    instance = this
                }
            }
    }
}