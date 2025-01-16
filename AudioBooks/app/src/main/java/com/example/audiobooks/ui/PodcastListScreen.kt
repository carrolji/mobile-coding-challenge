package com.example.audiobooks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.audiobooks.PodcastsViewModel
import com.example.audiobooks.R
import com.example.audiobooks.nav.Screen

@Composable
fun PodcastListScreen(
    viewModel: PodcastsViewModel,
    navController: NavHostController,
    onEvent: (PodcastUiEvent) -> Unit,
) {
    val podcastsUIState by viewModel.podcastsUiState.collectAsState()

    if (podcastsUIState.isLoading) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {
            items(podcastsUIState.podcastList) { podcast ->
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            onEvent(PodcastUiEvent.OnViewPodcastDetail(podcast.id))
                            navController.navigate(route = Screen.PodcastDetailScreen.route + "?podcastId=${podcast.id}")
                        },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AsyncImage(
                        model = podcast.thumbnails,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .height(70.dp)
                    )
                    Column {
                        Text(
                            text = podcast.title ?: "",
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(),
                        )
                        Text(
                            text = podcast.publisher ?: "",
                            fontStyle = FontStyle.Italic,
                            fontSize = 12.sp,
                            color = Color.Gray,
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .fillMaxWidth(),
                        )
                        Text(
                            text = if (podcast.favourite) "Favourited" else "",
                            fontSize = 12.sp,
                            color = colorResource(R.color.favorite),
                            modifier = Modifier
                                .padding(horizontal = 5.dp, vertical = 5.dp)
                                .fillMaxWidth(),
                        )
                    }
                }
                Divider(color = Color.LightGray, thickness = 0.5.dp)
            }
        }
    }
}