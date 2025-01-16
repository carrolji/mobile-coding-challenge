package com.example.audiobooks.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.audiobooks.ui.Podcast

@Entity
data class PodcastEntity(
    @PrimaryKey
    val id: String = "",
    val thumbnails: String? = null,
    val image: String? = null,
    val title: String? = null,
    val publisher: String? = null,
    val description: String? = null,
    var favourite: Boolean = false,
) {
    fun toModel() = Podcast(
        id = id,
        thumbnails = thumbnails,
        image = image,
        title = title,
        publisher = publisher,
        description = description,
        favourite = favourite
    )
}