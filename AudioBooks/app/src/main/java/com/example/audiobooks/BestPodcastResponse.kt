package com.example.audiobooks

import com.google.gson.annotations.SerializedName

data class BestPodcastResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("total")
    val total: Int,
    @SerializedName("has_next")
    val has_next: Boolean,
    @SerializedName("podcasts")
    val podcasts: List<PodcastResponse>?,
    @SerializedName("parent_id")
    val parent_id: Int,
    @SerializedName("page_number")
    val page_number: Int,
    @SerializedName("has_previous")
    val has_previous: Boolean,
    @SerializedName("listennotes_url")
    val listennotes_url: String? = null,
    @SerializedName("next_page_number")
    val next_page_number: Int,
    @SerializedName("previous_page_number")
    val previous_page_number: Int,
)

data class PodcastResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("rss")
    val rss: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("extra")
    val extra: ExtraResponse?,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("website")
    val website: String? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("genre_ids")
    val genre_ids: List<Int>?,
    @SerializedName("itunes_id")
    val itunes_id: Long,
    @SerializedName("publisher")
    val publisher: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: String? = null,
    @SerializedName("is_claimed")
    val is_claimed: Boolean,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("looking_for")
    val looking_for: LookingForResponse,
    @SerializedName("has_sponsors")
    val has_sponsors: Boolean,
    @SerializedName("listen_score")
    val listen_score: Int,
    @SerializedName("total_episodes")
    val total_episodes: String? = null,
    @SerializedName("listennotes_url")
    val listennotes_url: String? = null,
    @SerializedName("audio_length_sec")
    val audio_length_sec: Int,
    @SerializedName("explicit_content")
    val explicit_content: Boolean,
    @SerializedName("latest_episode_id")
    val latest_episode_id: String? = null,
    @SerializedName("latest_pub_date_ms")
    val latest_pub_date_ms: Long,
    @SerializedName("earliest_pub_date_ms")
    val earliest_pub_date_ms: Long,
    @SerializedName("has_guest_interviews")
    val has_guest_interviews: Boolean,
    @SerializedName("update_frequency_hours")
    val update_frequency_hours: Int,
    @SerializedName("listen_score_global_rank")
    val listen_score_global_rank: String? = null,
)

data class LookingForResponse(
    @SerializedName("guests")
    val guests: Boolean = false,
    @SerializedName("cohosts")
    val cohosts: Boolean = false,
    @SerializedName("sponsors")
    val sponsors: Boolean = false,
    @SerializedName("cross_promotion")
    val cross_promotion: Boolean = false,
)

data class ExtraResponse(
    @SerializedName("url1")
    val url1: String = "",
    @SerializedName("url2")
    val url2: String = "",
    @SerializedName("url3")
    val url3: String = "",
    @SerializedName("spotify_url")
    val spotify_url: String = "",
    @SerializedName("youtube_url")
    val youtube_url: String = "",
    @SerializedName("linkedin_url")
    val linkedin_url: String = "",
    @SerializedName("wechat_handle")
    val wechat_handle: String = "",
    @SerializedName("patreon_handle")
    val patreon_handle: String = "",
    @SerializedName("twitter_handle")
    val twitter_handle: String = "",
    @SerializedName("facebook_handle")
    val facebook_handle: String = "",
    @SerializedName("amazon_music_url")
    val amazon_music_url: String = "",
    @SerializedName("instagram_handle")
    val instagram_handle: String = "",
)