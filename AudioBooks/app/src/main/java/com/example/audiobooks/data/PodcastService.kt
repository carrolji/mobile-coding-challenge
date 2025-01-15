package com.example.audiobooks.data

import com.example.audiobooks.data.model.PodcastsRemoteResponse
import retrofit2.http.GET

interface PodcastService {

    @GET("best_podcasts")
    suspend fun getBestPodcasts(): PodcastsRemoteResponse
}