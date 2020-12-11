package com.example.album

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.album.data.AlbumDatabase
import com.example.album.data.AlbumRepo
import com.example.album.service.AlbumListService
import com.example.album.ui.ViewModelFactoryAlbumList
import java.util.concurrent.Executors

object Injection {
    private fun provideAlbumRepo(context: Context): AlbumRepo {
        val database = AlbumDatabase.getInstance(context)
        return AlbumRepo(AlbumListService.create(), database, Executors.newSingleThreadExecutor())
    }

    fun provideViewModelFactoryAlbumList(context: Context): ViewModelProvider.Factory {
        return ViewModelFactoryAlbumList(provideAlbumRepo(context))
    }
}