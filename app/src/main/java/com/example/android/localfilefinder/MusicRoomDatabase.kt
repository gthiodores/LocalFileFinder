package com.example.android.localfilefinder

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Music::class], version = 1)
abstract class MusicRoomDatabase : RoomDatabase() {
    abstract fun musicDAO() : MusicDAO

    companion object {
        @Volatile
        private var INSTANCE : MusicRoomDatabase? = null

        fun getDatabase(context : Context, scope : CoroutineScope) : MusicRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MusicRoomDatabase::class.java,
                    "music_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}