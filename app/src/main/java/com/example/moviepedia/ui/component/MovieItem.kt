package com.example.moviepedia.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviepedia.R
import com.example.moviepedia.ui.theme.MoviepediaTheme


@Composable
fun MovieItem(
    id: Long,
    title: String,
    image: Int,
    duration: String,
    year: Int,
    isWatchlist: Boolean,
    onWatchlistIconClick: (id: Long, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
            .clip(RoundedCornerShape(16.dp))
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = title,
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
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = year.toString())
                Text(text = duration)
            }
        }
        Icon(
            painter =
            if (isWatchlist){
                painterResource(id = R.drawable.baseline_bookmark_24)
            } else {
                painterResource(id = R.drawable.baseline_bookmark_border_24)
            },
            tint = if (isWatchlist) MaterialTheme.colorScheme.primary else Color.Black,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(24.dp)
                .clickable {
                    onWatchlistIconClick(id, !isWatchlist)
                }
                .testTag("watchlist_icon")
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFCE93D8)
@Composable
fun MovieItemPreview() {
    MoviepediaTheme {
        MovieItem(1,"Title", R.drawable.cover_thematrix, "2h", 2023, false, {_, _ ->})
    }
}