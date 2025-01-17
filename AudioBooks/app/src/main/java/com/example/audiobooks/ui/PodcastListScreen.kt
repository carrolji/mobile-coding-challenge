package com.example.audiobooks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.audiobooks.PodcastsViewModel

@Composable
fun PodcastListScreen(
    viewModel: PodcastsViewModel,
    navController: NavHostController,
    onEvent: (PodcastUiEvent) -> Unit,
) {
    val podcastsUIState by viewModel.podcastsUiState.collectAsState()

    if (podcastsUIState.podcastList.isEmpty()) {
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
            items(podcastsUIState.podcastList.size) { index ->
                val podcast = podcastsUIState.podcastList[index]
                PodcastItem(podcast, navController, onEvent)
                //check if index is at 10th item, load new item
                if(index >= podcastsUIState.podcastList.size -1 && !podcastsUIState.isLoading) {
                    onEvent(PodcastUiEvent.Pagination)
                }
            }
        }
    }
}