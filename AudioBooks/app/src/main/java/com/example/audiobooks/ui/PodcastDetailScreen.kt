package com.example.audiobooks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.audiobooks.PodcastsViewModel
import com.example.audiobooks.R

@Composable
fun PodcastDetailScreen(
    viewModel: PodcastsViewModel,
) {
    val podcast by viewModel.podcast.collectAsState()

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 15.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (podcast.id == "") {
            CircularProgressIndicator()
        } else {
            Text(
                text = podcast.title ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 20.dp)
            )
            Text(
                text = podcast.publisher ?: "",
                fontSize = 13.sp,
                color = Color.Gray,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(5.dp)
            )
            AsyncImage(
                model = podcast.image,
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .height(250.dp)
            )
            Button(
                onClick = { viewModel.favouriteAPodcast()},
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(R.color.favorite),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = if (podcast.favourite) "Favourited" else "Favourite",
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                )
            }
            Text(
                text = podcast.description ?: "",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier
                    .padding(top = 15.dp)
            )
        }
    }
}

//@Preview
//@Composable
//fun PodcastDetailScreenPreview() {
//    val mock = PodcastUIState(
//        id = "1",
//        title = "Star Wars 7x7: A Daily Bite-Sized Dose of Star Wars Joy",
//        publisher = "Star Wars 7x7",
//        favourite = true,
//        description = "The Star Wars 7x7 Podcast is Rebel-rousing fun for everyday Jedi, generally between 7 and 14 minutes a day, always 7 days a week. Join host Allen Voivod for Star Wars news, history, interviews, trivia, and deep dives into the Star Wars story as told in movies, books, comics, games, cartoons, and more. Follow now for your daily dose of Star Wars joy. It's destiny unleashed! #SW7x7"
//    )
//
//    PodcastDetailScreen(mock)
//
//}