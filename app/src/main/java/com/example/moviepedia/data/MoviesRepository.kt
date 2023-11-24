package com.example.moviepedia.data

import com.example.moviepedia.model.Movie
import com.example.moviepedia.model.MovieDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class MoviesRepository {

    private val movies = mutableListOf<Movie>()

    init {
        if (movies.isEmpty()){
            movies.addAll(MovieDataSource.movieList)
        }
    }

    fun getMoviesById(id: Long): Flow<Movie>{
        return flowOf(movies.first { it.id == id})
    }

    fun getWatchlistMovies(): Flow<List<Movie>> {
        return  flowOf(movies.filter { it.isWatchlist })
    }

    fun searchMovies(query : String) = flow {
        val data = movies.filter {
            it.title.contains(query, ignoreCase = true)
        }
        emit(data)
    }

    fun updateMovies(id: Long, newState: Boolean): Flow<Boolean> {
        val index = movies.indexOfFirst { it.id == id }
        val result = if (index >= 0){
            val movie = movies[index]
            movies[index] = movie.copy(isWatchlist = newState)
            true
        } else {
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