package com.example.album.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.album.R
import com.example.album.model.Album

class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val albumTitle: TextView = view.findViewById(R.id.singleTitle)
    private val albumTextId: TextView = view.findViewById(R.id.albumId)
    private val albumUrl: TextView = view.findViewById(R.id.albumUrl)
    private val albumThumbnailUrl: TextView = view.findViewById(R.id.thumbnailUrl)

    fun bind(album: Album?) {
        if (album != null) {
            showAlbumData(album)
        }
    }

    private fun showAlbumData(album: Album) {
        album.apply {
            albumTitle.text = title
            albumTextId.text = albumId.toString()
            albumUrl.text = url
            albumThumbnailUrl.text = thumbnailUrl
        }
    }

    companion object {
        fun create(parent: ViewGroup): AlbumViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.album_item, parent, false)
            return AlbumViewHolder(view)
        }
    }
}