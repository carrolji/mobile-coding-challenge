package com.example.audiobooks.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.audiobooks.PodcastsViewModel
import com.example.audiobooks.ui.PodcastDetailScreen
import com.example.audiobooks.ui.PodcastListScreen

@Composable
fun NavigationStack(viewModel: PodcastsViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.PodcastListScreen.route) {
        composable(route = Screen.PodcastListScreen.route) {
            PodcastListScreen(viewModel, navController, viewModel::onEvent)
        }
        composable(
            route = Screen.PodcastDetailScreen.route + "?podcastId={podcastId}",
            arguments = listOf(
                navArgument("podcastId") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            val podcastId = it.arguments?.getString("podcastId") ?: ""
            PodcastDetailScreen(viewModel, podcastId, navController, viewModel::onEvent)
        }
    }
}