package com.example.audiobooks.data.remote

import com.example.audiobooks.data.remote.model.PodcastResponse
import com.example.audiobooks.data.remote.model.PodcastsRemoteResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PodcastService {

    @GET("best_podcasts")
    suspend fun getBestPodcasts(
        @Query("page") page: Int? = null,
    ): PodcastsRemoteResponse

    @GET("podcasts/{id}")
    suspend fun getPodcastDetail(@Path("id") id: String): PodcastResponse
}