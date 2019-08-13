package com.example.android.localfilefinder

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music_list")
data class Music (@PrimaryKey val path : String, val title : String, val artist : String)