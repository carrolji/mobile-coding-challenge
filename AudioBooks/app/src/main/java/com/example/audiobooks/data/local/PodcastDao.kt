package com.example.audiobooks.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PodcastDao {

    @Upsert
    suspend fun upsertPodcastList(items: List<PodcastEntity>)

    @Query("SELECT * FROM PodcastEntity WHERE id = :id")
    suspend fun getPodcastById(id: String): PodcastEntity?

    @Query("SELECT * FROM PodcastEntity")
    suspend fun getPodcastList(): List<PodcastEntity>

    @Query("UPDATE PodcastEntity SET favourite = :isFavourite WHERE id = :id")
    suspend fun updateFavouritePodcast(id: String, isFavourite: Boolean)
}