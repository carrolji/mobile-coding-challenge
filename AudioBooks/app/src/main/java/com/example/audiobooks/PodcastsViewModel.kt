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
import com.example.audiobooks.ui.PodcastAction
import com.example.audiobooks.ui.PodcastUIState
import kotlinx.coroutines.flow.update

class PodcastsViewModel(
    private val podcastRepository: PodcastRepository
) : ViewModel() {

    private val _podcastsList = MutableStateFlow<List<PodcastUIState>>(emptyList())
    val podcastsList = _podcastsList.asStateFlow()

    private val _podcast = MutableStateFlow(PodcastUIState())
    val podcast = _podcast.asStateFlow()

    init {
        getPodcasts()
    }

    fun onAction(action: PodcastAction){
        when(action) {
            is PodcastAction.FavouriteAPodCast -> {
                updateFavouritePodcast(action.podcastId)
            }
            is PodcastAction.ViewPodcastDetail -> {

            }
        }
    }

    private fun getPodcasts() = viewModelScope.launch {
        podcastRepository.getPodcastList(true).collectLatest { result ->
            when(result) {
                is Result.Error -> Log.d("podcastsVM", "error: ${result.message}")
                is Result.Success -> {
                    result.data?.let { podcasts ->
                        _podcastsList.update {
                            podcasts
                        }
                    }
                }

                is Result.Loading -> {
                    Log.d("podcastsVM", "Loading")
                }
            }
        }
    }

    fun getPodcastDetail(podcastId: String) = viewModelScope.launch {
        podcastRepository.getPodcastDetail(podcastId).collectLatest { result ->
            when(result) {
                is Result.Error -> {
                    Log.d("podcastsVM", "error: ${result.message}")
                }
                is Result.Success -> {
                    result.data?.let { podcast ->
                        _podcast.update {
                            podcast
                        }

                    }
                }

                is Result.Loading -> {
                    Log.d("podcastsVM", "Loading")
                }
            }
        }
    }

    fun updateFavouritePodcast(podcastId: String) {
        _podcast.update {
            it.copy(favourite = !it.favourite)
        }

        val isFavourite = _podcast.value.favourite

        val podcastList = _podcastsList.value

        podcastList.first { it.id == podcastId }.favourite = isFavourite

        _podcastsList.update { podcastList }
    }
}