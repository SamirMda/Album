package com.example.album.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.album.data.AlbumRepo
import com.example.album.model.Album

class AlbumListViewModel(private val albumRepo: AlbumRepo) : ViewModel() {
    // list all albums
    val albumList : LiveData<List<Album>> = albumRepo.getAllAlbums()
}