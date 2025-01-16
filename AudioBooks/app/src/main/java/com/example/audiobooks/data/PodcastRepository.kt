package com.example.audiobooks.data

import android.util.Log
import com.example.audiobooks.data.local.PodcastDatabase
import com.example.audiobooks.data.remote.PodcastService
import com.example.audiobooks.data.remote.Result
import com.example.audiobooks.ui.Podcast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

interface PodcastRepository {
    fun getPodcastDetail(podcastId: String): Flow<Result<Podcast>>
    fun getPodcastList(forceFetchFromRemote: Boolean): Flow<Result<List<Podcast>>>
    fun updateFavouritePodcast(podcastId: String): Flow<Result<Podcast>>
}

class PodcastRepositoryImpl(
    private val api: PodcastService,
    private val podcastDatabase: PodcastDatabase
) : PodcastRepository {

    override fun getPodcastDetail(podcastId: String): Flow<Result<Podcast>> {
        return flow {
            Log.d("podcastRepo", "getPodcastDetail")
            emit(Result.Loading(true))
            val podcastEntity = podcastDatabase.podcastDao.getPodcastById(podcastId)
            if(podcastEntity != null) {
                emit(Result.Success(podcastEntity.toModel()))
                emit(Result.Loading(false))
            } else {
                emit(Result.Error(message = "Error no podcast"))
            }
        }
    }

    override fun getPodcastList(forceFetchFromRemote: Boolean): Flow<Result<List<Podcast>>> {
        return flow {
            Log.d("podcastRepo", "fetching podcast list")
            emit(Result.Loading(true))
            val localPodcastList = podcastDatabase.podcastDao.getPodcastList()

            val shouldFetchLocal = localPodcastList.isNotEmpty() && !forceFetchFromRemote

            if (shouldFetchLocal) {
                emit(Result.Success(localPodcastList.map { it.toModel() }))
                emit(Result.Loading(false))
                return@flow
            }

            val podcastsFromApi = try {
                api.getBestPodcasts().podcasts
            } catch (e: IOException) {
                emit(Result.Error(message = "Error loading podcasts"))
                return@flow
            } catch (e: HttpException) {
                emit(Result.Error(message = "Error loading podcasts"))
                return@flow
            }

            val podcastEntity = podcastsFromApi.let {
                it.map { podcastDto ->
                    podcastDto.toPodcastEntity()
                }
            }
            podcastDatabase.podcastDao.upsertPodcastList(podcastEntity)
            emit(Result.Success(podcastEntity.map { it.toModel() }))
            emit(Result.Loading(false))
        }
    }

    override fun updateFavouritePodcast(podcastId: String): Flow<Result<Podcast>> {
        return flow {
            Log.d("podcastRepo", "updateFavouritePodcast")
            val podcastEntity = podcastDatabase.podcastDao.getPodcastById(podcastId)
            if(podcastEntity != null) {
                val isFavourite = podcastEntity.favourite
                podcastDatabase.podcastDao.updateFavouritePodcast(podcastId, !isFavourite)
                val updatePodcast = podcastDatabase.podcastDao.getPodcastById(podcastId)
                if (updatePodcast != null) {
                    emit(Result.Success(updatePodcast.toModel()))
                }
            } else {
                emit(Result.Error(message = "Error no podcast"))
            }
        }
    }
}