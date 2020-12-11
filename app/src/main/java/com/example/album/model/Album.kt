package com.example.album.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "album")
data class Album(@PrimaryKey @field:SerializedName("id") val id: Int,
                 @field:SerializedName("albumId") val albumId: Int,
                 @field:SerializedName("title") val title: String = "",
                 @field:SerializedName("url") val url: String = "",
                 @field:SerializedName("thumbnailUrl") val thumbnailUrl: String = "")