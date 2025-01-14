package com.example.audiobooks

data class Podcast(
    val id: String,
    val thumbnails: String? = null,
    val image: String? = null,
    val title: String? = null,
    val publisher: String? = null,
    val description: String? = null,
    val favourite: Boolean = false,
)