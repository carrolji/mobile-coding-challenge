package com.example.audiobooks.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInterceptor {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val api: PodcastService = Retrofit.Builder()
        .baseUrl("https://listen-api-test.listennotes.com/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(PodcastService::class.java)
}