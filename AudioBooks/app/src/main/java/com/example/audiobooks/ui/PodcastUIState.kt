package com.example.audiobooks.ui

data class PodcastUIState(
    val id: String = "",
    val thumbnails: String? = null,
    val image: String? = null,
    val title: String? = null,
    val publisher: String? = null,
    val description: String? = null,
    var favourite: Boolean = false,
)