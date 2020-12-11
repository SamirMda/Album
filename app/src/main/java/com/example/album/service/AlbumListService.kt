package com.example.album.service

import android.util.Log
import com.example.album.model.Album
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val TAG = "AlbumListService"

fun requestAlbum(
    service: AlbumListService,
    onSuccess: (album: List<Album>) -> Unit,
    onError: (error: String) -> Unit) {

    service.getAlbum().enqueue(
        object : Callback<List<Album>> {
            override fun onFailure(call: Call<List<Album>>?, t: Throwable?) {
                Log.e(TAG, "fail to get data", t)
                onError(t!!.message ?: "unknown error")
            }

            override fun onResponse(
                call: Call<List<Album>>?,
                response: Response<List<Album>>?
            ) {
                Log.d(TAG, "got a response $response")
                if (response!!.isSuccessful) {
                    val albums = response.body() ?: emptyList()
                    onSuccess(albums)
                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
        }
    )
}

interface AlbumListService {

    /**
     * Get album list.
     */
    @GET("img/shared/technical-test.json")
    fun getAlbum(): Call<List<Album>>

    companion object {
        private const val BASE_URL = "https://static.leboncoin.fr/"

        fun create(): AlbumListService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AlbumListService::class.java)
        }
    }
}