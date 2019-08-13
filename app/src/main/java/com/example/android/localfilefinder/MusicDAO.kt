package com.example.android.localfilefinder

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MusicDAO {

    @Insert
    suspend fun insert(music : Music)

    @Update
    suspend fun update(music : Music)

    @Delete
    suspend fun  delete(music : Music)

    @Query("DELETE FROM music_list")
    fun deleteAll()

    @Query("SELECT * FROM music_list")
    fun fetchAllMusic() : LiveData<List<Music>>

}