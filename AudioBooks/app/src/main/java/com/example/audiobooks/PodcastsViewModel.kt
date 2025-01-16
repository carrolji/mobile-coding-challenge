package com.example.audiobooks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audiobooks.data.PodcastRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.audiobooks.data.remote.Result
import com.example.audiobooks.ui.PodcastUiEvent
import com.example.audiobooks.ui.PodcastItemUIState
import com.example.audiobooks.ui.UIState
import kotlinx.coroutines.flow.update

class PodcastsViewModel(
    private val podcastRepository: PodcastRepository
) : ViewModel() {

    private val _podcastsUiState = MutableStateFlow(UIState())
    val podcastsUiState = _podcastsUiState.asStateFlow()

    private val _podcastItemUiState = MutableStateFlow(PodcastItemUIState())
    val podcastItemUiState = _podcastItemUiState.asStateFlow()

    init {
        getPodcasts(false)
    }

    fun onEvent(event: PodcastUiEvent){
        when(event) {
            is PodcastUiEvent.OnFavouritePodcast -> {
                updateFavouritePodcast(event.podcastId)
            }
            is PodcastUiEvent.OnViewPodcastDetail -> {
                getPodcastDetail(event.podcastId)
            }

            PodcastUiEvent.Pagination -> {
                getPodcasts(true)
            }
        }
    }

    private fun getPodcasts(forceFetch: Boolean) = viewModelScope.launch {
        _podcastsUiState.update {
            it.copy(isLoading = true)
        }
        podcastRepository.getPodcastList(forceFetch).collectLatest { result ->
            when(result) {
                is Result.Error -> {
                    Log.d("podcastsVM", "error: ${result.message}")
                    _podcastsUiState.update {
                        it.copy(isLoading = false)
                    }
                }
                is Result.Success -> {
                    result.data?.let { podcasts ->
                        _podcastsUiState.update {
                            it.copy(podcastList = podcasts, isLoading = false)
                        }
                    }
                }

                is Result.Loading -> {
                    _podcastsUiState.update {
                        it.copy(isLoading = result.isLoading)
                    }
                }
            }
        }
    }

    fun getPodcastDetail(podcastId: String) = viewModelScope.launch {
        _podcastItemUiState.update {
            it.copy(isLoading = true)
        }
        podcastRepository.getPodcastDetail(podcastId).collectLatest { result ->
            when(result) {
                is Result.Error -> {
                    Log.d("podcastsVM", "error: ${result.message}")
                    _podcastItemUiState.update {
                        it.copy(isLoading = false)
                    }
                }
                is Result.Success -> {
                    result.data?.let { podcast ->
                        _podcastItemUiState.update {
                            it.copy(podcast = podcast, isLoading = false)
                        }
                    }
                }

                is Result.Loading -> {
                    _podcastItemUiState.update {
                        it.copy(isLoading = result.isLoading)
                    }
                }
            }
        }
    }

    fun updateFavouritePodcast(podcastId: String) = viewModelScope.launch {
        podcastRepository.updateFavouritePodcast(podcastId).collectLatest { result ->
            if (result is Result.Success) {
                result.data?.let { podcast ->
                    _podcastItemUiState.update {
                        it.copy(podcast = podcast, isLoading = false)
                    }
                }
                getPodcasts(false)
            }
        }
    }
}