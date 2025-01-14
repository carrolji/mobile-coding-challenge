package com.example.audiobooks

import retrofit2.Response
import retrofit2.http.GET

interface PodcastService {
    @GET("best_podcasts")
    suspend fun getBestPodcasts(): Response<BestPodcastResponse>
}