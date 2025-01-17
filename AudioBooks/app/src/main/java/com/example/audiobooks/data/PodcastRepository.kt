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
import kotlin.math.ceil
import kotlin.math.min

interface PodcastRepository {
    suspend fun maxPaging(): Int
    fun getPodcastDetail(podcastId: String): Flow<Result<Podcast>>
    fun getPodcastList(forceFetchFromRemote: Boolean, paging: Int): Flow<Result<List<Podcast>>>
    fun updateFavouritePodcast(podcastId: String): Flow<Result<Podcast>>
}

private const val LOAD_SIZE = 10

class PodcastRepositoryImpl(
    private val api: PodcastService,
    private val podcastDatabase: PodcastDatabase
) : PodcastRepository {

    override suspend fun maxPaging(): Int {
        return ceil((podcastDatabase.podcastDao.getAllPodcasts() / LOAD_SIZE).toDouble()).toInt()
    }

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

    override fun getPodcastList(forceFetchFromRemote: Boolean, paging: Int): Flow<Result<List<Podcast>>> {
        return flow {
            emit(Result.Loading(true))

            //Load 10 items at a time
            val podcastsSize = podcastDatabase.podcastDao.getAllPodcasts()
            val limitSize = min(LOAD_SIZE * paging, podcastsSize)
            val localPodcastList = podcastDatabase.podcastDao.getPodcastsLimit(limitSize)

            val shouldFetchLocal = localPodcastList.isNotEmpty() && !forceFetchFromRemote

            //Fetch from local db if podcast is empty and not force fetching
            if (shouldFetchLocal) {
                emit(Result.Success(localPodcastList.map { it.toModel() }))
                emit(Result.Loading(false))
                return@flow
            }

            //Fetch podcast from best podcasts endpoint
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
            //Insert data into db
            podcastDatabase.podcastDao.upsertPodcastList(podcastEntity)
            emit(Result.Success(podcastEntity.map { it.toModel() }))
            emit(Result.Loading(false))
        }
    }

    override fun updateFavouritePodcast(podcastId: String): Flow<Result<Podcast>> {
        return flow {
            //Get podcast by podcastId
            val podcastEntity = podcastDatabase.podcastDao.getPodcastById(podcastId)
            if(podcastEntity != null) {
                //Update favourite state to db
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