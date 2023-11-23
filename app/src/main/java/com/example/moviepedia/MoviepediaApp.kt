package com.example.moviepedia

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviepedia.ui.navigation.NavigationItem
import com.example.moviepedia.ui.navigation.Screen
import com.example.moviepedia.ui.screen.detail.DetailScreen
import com.example.moviepedia.ui.screen.home.HomeScreen
import com.example.moviepedia.ui.screen.profile.ProfileScreen
import com.example.moviepedia.ui.screen.watchlist.WatchlistScreen
import com.example.moviepedia.ui.theme.MoviepediaTheme

@Composable
fun MoviepediaApp(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailMovies.route) {
                BottomBar(navHostController = navHostController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navHostController,
            startDestination = Screen.Home.route,
            modifier = Modifier
                .padding(innerPadding)
        ) {
            composable(
                route = Screen.Home.route
            ) {
                HomeScreen(
                    navigateToDetail = { id ->
                        navHostController.navigate(Screen.DetailMovies.createRoute(id))
                    }
                )
            }
            composable(
                route = Screen.Watchlist.route
            ){
                WatchlistScreen()
            }
            composable(
                route = Screen.Profile.route
            ){
                ProfileScreen()
            }
            composable(
                route = Screen.DetailMovies.route,
                arguments = listOf(
                    navArgument("movieId"){
                        type = NavType.LongType
                    }
                )
            ){
                val id = it.arguments?.getLong("movieId") ?: 1L
                DetailScreen(
                    id = id
                )
            }
        }

    }
}

@Composable
fun BottomBar(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = modifier
    ) {
        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(id = R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_watchlist),
                icon = Icons.Default.ThumbUp,
                screen = Screen.Watchlist
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )

        navigationItem.map { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                label = {
                    Text(text = item.title)
                },
                onClick = {
                    navHostController.navigate(item.screen.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) }
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MoviepediaAppPreview() {
    MoviepediaTheme {
        MoviepediaApp()
    }
}