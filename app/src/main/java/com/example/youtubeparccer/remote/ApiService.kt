package com.example.youtubeparccer.remote

import android.provider.MediaStore
import android.telecom.Call

interface ApiService {

    @GET("playlists")
    fun getPlaylist(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") key: String,
        @Query("maxResults") maxResults: Int = 20
    ): Call<MediaStore.Audio.Playlists>
}
