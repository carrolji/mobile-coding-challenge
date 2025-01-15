package com.example.audiobooks.data

import com.example.audiobooks.data.model.Podcast
import com.example.audiobooks.data.model.PodcastsRemoteResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PodcastService {

    @GET("best_podcasts")
    suspend fun getBestPodcasts(): PodcastsRemoteResponse

    @GET("podcasts/{id}")
    suspend fun getPodcastDetail(@Path("id") id: String): Podcast
}