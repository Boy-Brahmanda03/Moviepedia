package com.example.moviepedia.data

import com.example.moviepedia.model.MovieDataSource
import com.example.moviepedia.model.UserMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class MoviesRepository {

    private val userMovies = mutableListOf<UserMovies>()

    init {
        if (userMovies.isEmpty()) {
            MovieDataSource.movieList.forEach {
                userMovies.add(UserMovies(it, isOnWatchList = false))
            }
        }
    }

    fun getAllMovies(): Flow<List<UserMovies>> = flowOf(userMovies)

    fun getUserMoviesById(id: Long): UserMovies {
        return userMovies.first {
            it.movie.id == id
        }
    }

    fun getWatchListMovies(): Flow<List<UserMovies>> {
        return getAllMovies()
            .map {
                it.filter { userMovie ->
                    userMovie.isOnWatchList
                }
            }
    }

    fun addToWatchList(id: Long, isOnWatchList: Boolean): Flow<Boolean> {
        val index = userMovies.indexOfFirst { it.movie.id == id }
        val movieToUpdate = userMovies[index]
        val result =
            if (movieToUpdate.isOnWatchList != isOnWatchList) {
                // Movie ditemukan dan status watchlist berbeda, perbarui statusnya
                userMovies[index] = movieToUpdate.copy(isOnWatchList = isOnWatchList)
                true
            } else {
                // Movie ditemukan tetapi status watchlist sama, tidak ada perubahan
                false
            }
        return flowOf(result)
    }


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