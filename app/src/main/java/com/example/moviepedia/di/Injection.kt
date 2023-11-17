package com.example.moviepedia.di

import com.example.moviepedia.data.MoviesRepository

object Injection {
    fun provideRepository(): MoviesRepository {
        return MoviesRepository.getInstance()
    }
}