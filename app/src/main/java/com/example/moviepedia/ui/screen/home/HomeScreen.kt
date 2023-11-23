package com.example.moviepedia.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviepedia.di.Injection
import com.example.moviepedia.model.Movie
import com.example.moviepedia.ui.ViewModelFactory
import com.example.moviepedia.ui.common.UiState
import com.example.moviepedia.ui.component.MovieItem
import com.example.moviepedia.ui.component.MySearchBar
import com.example.moviepedia.ui.theme.MoviepediaTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit
) {
    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.search(query)
            }
            is UiState.Success -> {
                HomeContent(
                    query = query,
                    onQueryChange = viewModel::search,
                    movies = uiState.data,
                    onWatchlistIconClick = { id, newState ->
                        viewModel.updateMovies(id, newState)
                    },
                    navigateToDetail = navigateToDetail,
                    modifier = modifier
                )
            }
            is UiState.Error -> {}
        }

    }
}

@Composable
fun HomeContent(
    query: String,
    onQueryChange: (String) -> Unit,
    movies: List<Movie>,
    onWatchlistIconClick : (Long, Boolean) -> Unit,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column (modifier = modifier) {
        MySearchBar(query = query, onQueryChange = onQueryChange)
        if (movies.isEmpty()){
            Text(text = "Kosong")
        } else {
            MovieList(
                movies = movies,
                onWatchlistIconClick = onWatchlistIconClick,
                navigateToDetail = navigateToDetail
            )
        }
    }
}

@Composable
fun MovieList(
    movies: List<Movie>,
    onWatchlistIconClick : (Long, Boolean) -> Unit,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(8.dp)
    ) {
        items(movies, key = { it.id }) { movie ->
            MovieItem(
                id = movie.id,
                title = movie.title,
                image = movie.imageCover,
                duration = movie.duration,
                year = movie.releasedYear,
                isWatchlist = movie.isWatchlist ,
                onWatchlistIconClick = onWatchlistIconClick,
                modifier = Modifier
                    .clickable {
                        navigateToDetail(movie.id)
                    }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MoviepediaTheme {
        HomeScreen(navigateToDetail = {})
    }
}

