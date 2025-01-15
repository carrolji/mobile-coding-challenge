package com.example.audiobooks.data

import com.example.audiobooks.data.model.Podcast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException

interface PodcastRepository {
    fun getBestPodcasts(): Flow<Result<List<Podcast>>>
}

class PodcastRepositoryImpl(
    private val api: PodcastService
): PodcastRepository {

    override fun getBestPodcasts(): Flow<Result<List<Podcast>>> {
        return flow {
            val podcastsFromApi = try {
                api.getBestPodcasts().podcasts
            } catch (e: IOException) {
                emit(Result.Error(message = "Error loading podcasts"))
                return@flow
            }

            emit(Result.Success(podcastsFromApi))
        }
    }
}