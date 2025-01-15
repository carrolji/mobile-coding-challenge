package com.example.audiobooks.nav

sealed class Screen(val route: String) {
    object PodcastListScreen: Screen("podcast_list_screen")
    object PodcastDetailScreen: Screen("podcast_detail_screen")
}