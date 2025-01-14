package com.example.audiobooks

import android.util.Log
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PodcastRepository {

    var podcastCache: Map<Int, Podcast> = hashMapOf()

    val api: PodcastService = Retrofit.Builder()
        .baseUrl("https://listen-api-test.listennotes.com/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PodcastService::class.java)

//    fun getBestPodcasts(): Response<List<BestPodcastResponse>> {
//        return api.getBestPodcasts()
//    }
}