package com.example.album.data

import androidx.lifecycle.LiveData
import com.example.album.db.AlbumDao
import com.example.album.model.Album
import com.example.album.service.AlbumListService
import com.example.album.service.requestAlbum
import java.util.concurrent.Executor

class AlbumRepo(private val albumListService: AlbumListService, private val albumDao: AlbumDao, private val ioExecutor: Executor) {
    var TEST_MODE = false

    fun getAllAlbums(): LiveData<List<Album>> {
        if (!TEST_MODE) {
            requestAlbum(albumListService, {
                    albums ->
                ioExecutor.execute {
                    albumDao.insertAll(albums)
                }
            }, { error ->

            })
        }

        return albumDao.getAllAlbums()
    }
}