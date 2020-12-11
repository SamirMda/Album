package com.example.album.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.album.data.AlbumRepo
import java.lang.IllegalArgumentException

class ViewModelFactoryAlbumList(private val albumRepo: AlbumRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AlbumListViewModel(albumRepo) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}