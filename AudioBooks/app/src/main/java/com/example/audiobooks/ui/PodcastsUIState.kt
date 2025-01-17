package com.example.audiobooks.ui

data class PodcastsUIState(
    val isLoading: Boolean = false,
    var podcastListPage: Int = 1,
    val podcastList: List<Podcast> = emptyList()
)

data class PodcastItemUIState(
    val isLoading: Boolean = false,
    val podcast: Podcast = Podcast()
)