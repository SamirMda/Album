package com.example.album.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.album.model.Album

@Dao
interface AlbumDao {
    // Add a list of albums
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(albumList: List<Album>)

    // Get all the album
    @Query("SELECT * FROM album")
    fun getAllAlbums(): LiveData<List<Album>>
}