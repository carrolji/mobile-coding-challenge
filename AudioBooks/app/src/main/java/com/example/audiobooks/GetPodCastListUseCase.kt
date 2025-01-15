package com.example.audiobooks

import com.example.audiobooks.data.PodcastRepository
import com.example.audiobooks.data.PodcastRepositoryImpl
import com.example.audiobooks.data.PodcastService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import com.example.audiobooks.data.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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