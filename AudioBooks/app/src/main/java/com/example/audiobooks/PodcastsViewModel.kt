package com.example.audiobooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audiobooks.data.PodcastRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.audiobooks.data.Result
import kotlinx.coroutines.flow.update

class PodcastsViewModel(
    private val podcastRepository: PodcastRepository
) : ViewModel() {

    //val getPodCastListUseCase = GetPodCastListUseCase(podcastRepository)
    private val _podcastsList = MutableStateFlow<List<PodcastUIState>>(emptyList())
    val podcastsList = _podcastsList.asStateFlow()

    fun getPodcasts() = viewModelScope.launch {
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
}