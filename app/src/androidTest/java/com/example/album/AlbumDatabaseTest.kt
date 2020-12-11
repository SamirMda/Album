package com.example.album

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.album.data.AlbumDatabase
import com.example.album.data.AlbumRepo
import com.example.album.db.AlbumDao
import com.example.album.model.Album
import com.example.album.ui.AlbumListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class AlbumDatabaseTest {
    private var albumDao: AlbumDao? = null
    private var albumListViewModel: AlbumListViewModel ?= null
    private val TAG = "AlbumTest"

    @Before
    fun setup() {
        AlbumDatabase.TEST_MODE = true
        albumDao = AlbumDatabase.getInstance(getApplicationContext())
    }

    @Test
    fun should_Insert_All_Albums_Data() {
        val albums:MutableList<Album> = mutableListOf()
        albums.add(Album(1, 1, "single1", "www.test.com", "www.thumbnail.com"))
        albums.add(Album(2, 1, "single2", "www.test2.com", "www.thumbnail2.com"))
        albums.add(Album(3, 2, "single3", "www.test3.com", "www.thumbnail3.com"))
        albumDao?.insertAll(albums)

        val albumsTest = getValue(albumDao?.getAllAlbums()!!)

        Assert.assertEquals(albums, albumsTest)
    }

    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        GlobalScope.launch(Dispatchers.Main) {
            val observer = object : Observer<T> {
                override fun onChanged(t: T?) {
                    data[0] = t
                    latch.countDown()
                    liveData.removeObserver(this)//To change body of created functions use File | Settings | File Templates.
                }

            }

            liveData.observeForever(observer)
            latch.await(2, TimeUnit.SECONDS)
        }

        /*val observer = object : Observer<T> {
            override fun onChanged(t: T?) {
                data[0] = t
                latch.countDown()
                liveData.removeObserver(this)//To change body of created functions use File | Settings | File Templates.
            }

        }
        liveData.observeForever(observer)
//        latch.await(2, TimeUnit.SECONDS)*/

        return data[0] as T
    }
}