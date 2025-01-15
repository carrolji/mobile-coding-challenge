package com.example.audiobooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.audiobooks.data.PodcastRepositoryImpl
import com.example.audiobooks.data.RetrofitInterceptor
import com.example.audiobooks.nav.NavigationStack

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<PodcastsViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PodcastsViewModel(PodcastRepositoryImpl(RetrofitInterceptor.api)) as T
            }
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationStack(viewModel)
        }
    }
}