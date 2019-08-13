package com.example.android.localfilefinder

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MusicViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : MusicRepository
    val allMusic : LiveData<List<Music>>

    init {
        val musicDAO = MusicRoomDatabase.getDatabase(application, viewModelScope).musicDAO()
        repository = MusicRepository(musicDAO)
        allMusic = repository.allMusic
    }

    fun insert(music : Music) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(music)
    }
}