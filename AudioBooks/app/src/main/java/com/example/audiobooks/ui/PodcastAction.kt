package com.example.audiobooks.ui

sealed interface PodcastAction {
    data class FavouriteAPodCast(val podcastId: String): PodcastAction
    data class ViewPodcastDetail(val podcastId: String): PodcastAction
}