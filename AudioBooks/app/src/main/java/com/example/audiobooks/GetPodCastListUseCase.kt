package com.example.audiobooks

import com.example.audiobooks.data.PodcastRepository

class GetPodCastListUseCase(
    private val podcastRepository: PodcastRepository
) {
//    suspend operator fun invoke(): List<PodcastUIState> = withContext(Dispatchers.IO) {
//        val response = repository.getBestPodcasts().collectLatest { result ->
//            when(result) {
//                is Result.Error -> TODO()
//                is Result.Success -> {
//
//
//                }
//            }
//        }
//
//        return@withContext if (response.isSuccessful) {
//            val podcastsResponse = response.body()
//            podcastsResponse?.podcasts?.map {
//                PodcastUIState(
//                    id = it.id,
//                    thumbnails = it.thumbnail,
//                    image = it.image,
//                    title = it.title,
//                    publisher = it.publisher,
//                    description = it.description,
//                )
//            } ?: emptyList()
//        } else emptyList<PodcastUIState>()
//    }
}