package com.example.android.localfilefinder

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class MusicRepository(private val musicDAO: MusicDAO) {
    val allMusic : LiveData<List<Music>> = musicDAO.fetchAllMusic()

    @WorkerThread
    suspend fun insert(music : Music) {
        musicDAO.insert(music)
    }
}