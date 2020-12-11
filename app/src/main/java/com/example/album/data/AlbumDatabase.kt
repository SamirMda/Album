package com.example.album.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.album.db.AlbumDao
import com.example.album.model.Album

/**
 *  Database to store all the albums
 */
@Database(
    entities = [Album::class],
    version = 1,
    exportSchema = false
)
abstract class AlbumDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao

    companion object {
        var TEST_MODE = false
        private const val databaseName = "album.db"
        private var db: AlbumDatabase? = null
        private var dbInstance: AlbumDao? = null

        fun getInstance(context: Context): AlbumDao {
            if (dbInstance == null) {
                if (TEST_MODE) {
                    db = Room.inMemoryDatabaseBuilder(context, AlbumDatabase::class.java).allowMainThreadQueries().build()
                    dbInstance = db?.albumDao()
                } else {
                    db = Room.databaseBuilder(context, AlbumDatabase::class.java, databaseName).build()
                    dbInstance = db?.albumDao()
                }
            }
            return dbInstance!!
        }
    }
}