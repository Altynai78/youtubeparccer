package com.example.youtubeparccer.ui

import android.content.pm.LauncherApps
import android.provider.MediaStore
import android.telecom.Call
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeparccer.remote.ApiService
import com.example.youtubeparccer.remote.RetrofitClient

class MainViewModel: ViewModel(){

    private val apiService: ApiService = RetrofitClient.create()

    fun getPlaylist(): LiveData<MediaStore.Audio.Playlists> {
        return playlists()
    }

    private fun playlists(): LiveData<MediaStore.Audio.Playlists> {
        val data = MutableLiveData<MediaStore.Audio.Playlists>()

        apiService.getPlaylist(
            "snippet,contentDetails",
            "UCWOA1ZGywLbqmigxE4Qlvuw",
            BuildConfig.API_KEY,
        ).enqueue(object: LauncherApps.Callback<MediaStore.Audio.Playlists> {
            override fun onResponse(call: Call<MediaStore.Audio.Playlists>, response: Response<MediaStore.Audio.Playlists>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<MediaStore.Audio.Playlists>, t: Throwable) {
                print(t.stackTrace)
            }
        })

        return data
    }

}