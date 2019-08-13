package com.example.android.localfilefinder

import android.app.Activity
import android.content.Intent
import android.media.MediaMetadataRetriever
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var musicViewModel : MusicViewModel
    private lateinit var musicAdapter : MusicAdapter

    companion object {
        const val NEW_MUSIC_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        musicAdapter = MusicAdapter(this)
        recycler.adapter = musicAdapter
        recycler.layoutManager = LinearLayoutManager(this)

        musicViewModel = ViewModelProviders.of(this).get(MusicViewModel::class.java)
        musicViewModel.allMusic.observe(this, Observer {
            musicAdapter.setMusics(it)
        })

        fab.setOnClickListener {
            val intent : Intent = Intent(Intent.ACTION_GET_CONTENT).apply { type = "audio/*" }
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
            startActivityForResult(Intent.createChooser(intent, "Complete Action Using"), NEW_MUSIC_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_MUSIC_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val audioURI = data?.data
            val mediaData = MediaMetadataRetriever()

            if (audioURI != null) {
                val audioPath = audioURI.path!!.split(":")[1]
                mediaData.setDataSource(audioPath)

                val audioArtist = mediaData.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
                val audioTitle = mediaData.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
                val newMusic = Music(path = audioPath, artist = audioArtist, title = audioTitle)

                musicViewModel.insert(newMusic)
            }
        }
    }
}
