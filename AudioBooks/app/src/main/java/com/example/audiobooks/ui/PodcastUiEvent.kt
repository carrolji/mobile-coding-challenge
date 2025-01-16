package com.example.audiobooks.ui

sealed interface PodcastUiEvent {
    object Pagination: PodcastUiEvent
    data class OnFavouritePodcast(val podcastId: String): PodcastUiEvent
    data class OnViewPodcastDetail(val podcastId: String): PodcastUiEvent
}