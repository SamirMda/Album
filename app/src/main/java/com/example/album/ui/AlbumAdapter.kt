package com.example.album.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.album.model.Album

class AlbumAdapter : ListAdapter<Album, RecyclerView.ViewHolder>(ALBUM_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AlbumViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            (holder as AlbumViewHolder).bind(repoItem)
        }
    }

    companion object {
        private val ALBUM_COMPARATOR = object : DiffUtil.ItemCallback<Album>() {
            override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean =
                oldItem.albumId == newItem.albumId
        }
    }
}