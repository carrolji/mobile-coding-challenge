package com.example.audiobooks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun PodcastDetailScreen(podcast: Podcast) {

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = podcast.title ?: "",
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(5.dp)
        )
        Text(
            text = podcast.publisher ?: "",
            fontSize = 12.sp,
            color = Color.Gray,
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .padding(horizontal = 5.dp)
        )
        AsyncImage(
            model = podcast.thumbnails,
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .clip(RoundedCornerShape(10.dp))
                .height(70.dp)
        )
        //Button { Text(text = "Favorite") }
        Text(
            text = podcast.description ?: "",
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier
                .padding(horizontal = 5.dp)
        )
    }

}

@Preview
@Composable
fun PodcastDetailScreenPreview() {
    val mock = Podcast(
        id = "1",
        title = "WorkLife",
        publisher = "Ted",
        favourite = true,
        description = "hello"
    )

    PodcastDetailScreen(mock)

}