package com.example.audiobooks.data.remote.model

import com.example.audiobooks.data.local.PodcastEntity

data class PodcastResponse(
    val audio_length_sec: Int,
    val country: String,
    val description: String,
    val earliest_pub_date_ms: Long,
    val email: String,
    val explicit_content: Boolean,
    val extra: Extra?,
    val genre_ids: List<Int>,
    val has_guest_interviews: Boolean,
    val has_sponsors: Boolean,
    val id: String,
    val image: String,
    val is_claimed: Boolean,
    val itunes_id: Int,
    val language: String,
    val latest_episode_id: String,
    val latest_pub_date_ms: Long,
    val listen_score: Int,
    val listen_score_global_rank: String,
    val listennotes_url: String,
    val looking_for: LookingFor,
    val publisher: String,
    val rss: String,
    val thumbnail: String,
    val title: String,
    val total_episodes: Int,
    val type: String,
    val update_frequency_hours: Int,
    val website: String
) {
    fun toPodcastEntity() = PodcastEntity(
        id = id,
        thumbnails = thumbnail,
        image = image,
        title = title,
        publisher = publisher,
        description = description,
        favourite = false
    )
}