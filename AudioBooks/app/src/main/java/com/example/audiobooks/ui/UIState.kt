package com.example.audiobooks.ui

data class UIState(
    val isLoading: Boolean = false,
    val podcastListPage: Int = 1,
    val podcastList: List<Podcast> = emptyList()
)

data class PodcastItemUIState(
    val isLoading: Boolean = false,
    val podcast: Podcast = Podcast()
)