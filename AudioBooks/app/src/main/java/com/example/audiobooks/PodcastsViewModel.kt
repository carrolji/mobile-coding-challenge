package com.example.audiobooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audiobooks.data.PodcastRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.audiobooks.data.Result
import com.example.audiobooks.ui.PodcastUIState
import kotlinx.coroutines.flow.update

class PodcastsViewModel(
    private val podcastRepository: PodcastRepository
) : ViewModel() {

    private val _podcastsList = MutableStateFlow<List<PodcastUIState>>(emptyList())
    val podcastsList = _podcastsList.asStateFlow()

    private val _podcast = MutableStateFlow(PodcastUIState(id = ""))
    val podcast = _podcast.asStateFlow()

    init {
        getPodcasts()
    }

    private fun getPodcasts() = viewModelScope.launch {
        podcastRepository.getBestPodcasts().collectLatest { result ->
            when(result) {
                is Result.Error -> TODO()
                is Result.Success -> {
                    result.data?.let { podcasts ->
                        val updateItem = podcasts.map {
                            PodcastUIState(
                                id = it.id,
                                thumbnails = it.thumbnail,
                                image = it.image,
                                title = it.title,
                                publisher = it.publisher,
                                description = it.description,
                            )
                        }
                        _podcastsList.update {
                            updateItem
                        }
                    }
                }
            }
        }
    }

    fun getPodcastDetail(podcastId: String) = viewModelScope.launch {
        podcastRepository.getPodcastDetail(podcastId).collectLatest { result ->
            when(result) {
                is Result.Error -> TODO()
                is Result.Success -> {
                    result.data?.let { podcast ->
                        val updateItem = PodcastUIState(
                            id = podcast.id,
                            thumbnails = podcast.thumbnail,
                            image = podcast.image,
                            title = podcast.title,
                            publisher = podcast.publisher,
                            description = podcast.description,
                        )
                        _podcast.update {
                            updateItem
                        }
                    }
                }
            }
        }
    }

    fun favouriteAPodcast() {
        _podcast.update {
            it.copy(favourite = !it.favourite)
        }
    }
}