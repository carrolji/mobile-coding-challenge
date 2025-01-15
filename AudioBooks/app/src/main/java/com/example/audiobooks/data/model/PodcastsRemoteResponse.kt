package com.example.audiobooks.data.model

data class PodcastsRemoteResponse(
    val has_next: Boolean,
    val has_previous: Boolean,
    val id: Int,
    val listennotes_url: String,
    val name: String,
    val next_page_number: Int,
    val page_number: Int,
    val parent_id: Int,
    val podcasts: List<Podcast>,
    val previous_page_number: Int,
    val total: Int
)