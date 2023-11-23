package com.example.moviepedia.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviepedia.R
import com.example.moviepedia.di.Injection
import com.example.moviepedia.model.UserMovies
import com.example.moviepedia.ui.ViewModelFactory
import com.example.moviepedia.ui.common.UiState
import com.example.moviepedia.ui.component.MovieItem
import com.example.moviepedia.ui.component.MovieItemList
import com.example.moviepedia.ui.component.SectionText
import com.example.moviepedia.ui.theme.MoviepediaTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllMovies()
            }

            is UiState.Success -> {
                val data = uiState.data
                HomeContent(
                    movies = data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    addToWatchlist = { id, isOnWatchlist ->
                        viewModel.addWatchlist(id, isOnWatchlist)
                    }
                )
            }

            is UiState.Error -> {
            }
        }
    }
}

@Composable
fun HomeContent(
    movies: List<UserMovies>,
    navigateToDetail: (Long) -> Unit,
    addToWatchlist: (Long, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {

        item {
            SearchBarMovie()
        }

        item {
            SectionText(titleSection = stringResource(id = R.string.trending_section))
        }

        item {
            ForYouMoviesSection(
                movies = movies.slice(5..9),
                navigateToDetail = navigateToDetail
            )
        }

        item {
            SectionText(titleSection = stringResource(id = R.string.top_movie_section))
        }

        items(movies, key = { it.movie.id }) {
            MovieItemList(
                userMovies = it,
                addToWatchlist = addToWatchlist,
                modifier = Modifier
                    .clickable { navigateToDetail(it.movie.id) }
            )
        }
    }
}

@Composable
fun ForYouMoviesSection(
    movies: List<UserMovies>,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(8.dp),
    ) {
        items(movies) { item: UserMovies ->
            MovieItem(
                userMovies = item,
                modifier = modifier
                    .clickable {
                        navigateToDetail(item.movie.id)
                    }
                    .fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarMovie() {
    SearchBar(
        query = "",
        onQueryChange = {},
        onSearch = {},
        active = false,
        onActiveChange = {},
        trailingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        placeholder = {
            Text(text = "Cari movie")
        }
    ) {
    }
}

@Preview
@Composable
fun SearchBarMoviePreview() {
    MoviepediaTheme {
        SearchBarMovie()
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MoviepediaTheme {
        HomeScreen(navigateToDetail = {})
    }
}

