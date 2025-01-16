package com.example.audiobooks.data.remote

import com.example.audiobooks.data.remote.model.PodcastResponse
import com.example.audiobooks.data.remote.model.PodcastsRemoteResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PodcastService {

    @GET("best_podcasts")
    suspend fun getBestPodcasts(): PodcastsRemoteResponse

    @GET("podcasts/{id}")
    suspend fun getPodcastDetail(@Path("id") id: String): PodcastResponse
}