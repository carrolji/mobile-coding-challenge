package com.example.audiobooks

import com.example.audiobooks.PodcastRepository.api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPodCastListUseCase() {
    suspend operator fun invoke(): List<Podcast> = withContext(Dispatchers.IO) {
        val response = api.getBestPodcasts()

        return@withContext if (response.isSuccessful) {
            val podcastsResponse = response.body()
            podcastsResponse?.podcasts?.map {
                Podcast(
                    id = it.id,
                    thumbnails = it.thumbnail,
                    image = it.image,
                    title = it.title,
                    publisher = it.publisher,
                    description = it.description,
                )
            } ?: emptyList()
        } else emptyList<Podcast>()
    }
}