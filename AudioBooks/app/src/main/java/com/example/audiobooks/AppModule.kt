package com.example.audiobooks

import androidx.room.Room
import com.example.audiobooks.data.PodcastRepository
import com.example.audiobooks.data.PodcastRepositoryImpl
import com.example.audiobooks.data.local.PodcastDao
import com.example.audiobooks.data.local.PodcastDatabase
import com.example.audiobooks.data.remote.PodcastService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val OKHTTP_CLIENT: String = "OkHttp"
private const val HOST = "https://listen-api-test.listennotes.com/api/v2/"
private const val ROOM_DB: String = "podcast-db"

val appModule = module {

    factory<OkHttpClient>(named(OKHTTP_CLIENT)) {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single<PodcastService> {
        Retrofit.Builder()
            .client(get(named(OKHTTP_CLIENT)))
            .baseUrl(HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PodcastService::class.java)
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            PodcastDatabase::class.java,
            ROOM_DB
        ).build()
    }

    single<PodcastDao> {
        val database = get<PodcastDatabase>()
        database.podcastDao
    }

    single<PodcastRepository> {
        PodcastRepositoryImpl(get(), get())
    }

    viewModel {
        PodcastsViewModel(get())
    }
}