package com.example.audiobooks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _podcastsList = MutableStateFlow<List<Podcast>>(emptyList())
    val podcastsList: StateFlow<List<Podcast>>
        get() = _podcastsList

    fun getPodcasts() = viewModelScope.launch(Dispatchers.IO){
        _podcastsList.value = GetPodCastListUseCase().invoke()
    }
}