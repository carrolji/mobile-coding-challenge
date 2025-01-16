package com.example.audiobooks.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PodcastEntity::class],
    version = 1
)
abstract class PodcastDatabase: RoomDatabase() {
    abstract val podcastDao: PodcastDao
}