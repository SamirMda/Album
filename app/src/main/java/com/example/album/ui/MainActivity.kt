package com.example.album.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.album.Injection
import com.example.album.R
import com.example.album.model.Album
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModelAlbumList: AlbumListViewModel
    private var adapter = AlbumAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelAlbumList = ViewModelProvider(this, Injection.provideViewModelFactoryAlbumList(this))
            .get(AlbumListViewModel::class.java)

        initAdapter()
    }

    private fun initAdapter() {
        list.layoutManager = LinearLayoutManager(applicationContext)
        list.adapter = adapter

        /**
         * Observe changes in the list of album
         */
        viewModelAlbumList.albumList.observe(this, Observer<List<Album>> {
            adapter.submitList(it)
        })
    }
}