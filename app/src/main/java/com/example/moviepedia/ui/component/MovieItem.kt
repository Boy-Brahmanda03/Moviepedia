package com.example.moviepedia.ui.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.moviepedia.R
import com.example.moviepedia.model.MovieDataSource
import com.example.moviepedia.model.UserMovies
import com.example.moviepedia.ui.theme.MoviepediaTheme

@Composable
fun MovieItem(
    userMovies: UserMovies,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
            .width(135.dp)
            .height(270.dp)
    ) {
        ConstraintLayout(
            modifier = modifier.fillMaxSize()
        ) {
            val (cImage, cTitle, row) = createRefs()

            Image(
                painter = painterResource(id = userMovies.movie.imageCover),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .height(200.dp)
                    .constrainAs(cImage) {
                        top.linkTo(parent.top)
                    }
            )

            Text(
                text = userMovies.movie.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .constrainAs(cTitle) {
                        top.linkTo(cImage.bottom)
                    }
                    .padding(8.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .constrainAs(row) {
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(8.dp)
            ) {
                Text(text = userMovies.movie.releasedYear.toString())
                Text(text = userMovies.movie.duration)
            }

        }
    }
}

@Composable
fun MovieItemList(
    userMovies: UserMovies,
    addToWatchlist: (id: Long, isOnWatchlist: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
            .wrapContentSize()
            .padding(8.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = userMovies.movie.imageCover),
                contentDescription = userMovies.movie.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier.height(150.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .weight(2f)
                    .padding(8.dp)
            ) {
                Text(
                    text = userMovies.movie.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = userMovies.movie.releasedYear.toString())
                    Text(text = userMovies.movie.duration)
                }
                Text(
                    text = userMovies.movie.storyline,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
            IconToggleButton(
                checked = false,
                onCheckedChange = {
                    addToWatchlist(userMovies.movie.id, userMovies.isOnWatchList == !it)
                    Log.d("TAG", "MovieItemList: ${userMovies.isOnWatchList} == $it")
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.bookmark_border),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFCE93D8)
@Composable
fun MovieItemPreview() {
    MoviepediaTheme {
        MovieItem(userMovies = UserMovies(movie = MovieDataSource.movieList[0], false))
    }
}

@Preview(showSystemUi = true)
@Composable
fun MovieItemListPreview() {
    MoviepediaTheme {
    }
}