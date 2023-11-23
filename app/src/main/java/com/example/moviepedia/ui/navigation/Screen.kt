package com.example.moviepedia.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Watchlist: Screen("watchlist")

    object Profile: Screen("profile")
    object DetailMovies : Screen("home/{movieId}"){
        fun createRoute(movieId: Long): String = "home/$movieId"
    }
}