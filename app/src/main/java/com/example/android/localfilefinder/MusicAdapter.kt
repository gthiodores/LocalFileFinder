package com.example.android.localfilefinder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MusicAdapter internal constructor(context : Context) : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    private var musics = emptyList<Music>()

    inner class MusicViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val musicItemName = itemView.findViewById<TextView>(R.id.item_name)
        val musicItemArtist = itemView.findViewById<TextView>(R.id.item_artist)
        val musicItemPath = itemView.findViewById<TextView>(R.id.item_path)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return MusicViewHolder(inflater.inflate(R.layout.recycler_item, parent, false))
    }

    override fun getItemCount() = musics.size

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val current = musics[position]
        holder.musicItemName.text = current.title
        holder.musicItemArtist.text = current.artist
        holder.musicItemPath.text = current.path
    }

    internal fun setMusics(musics : List<Music>) {
        this.musics = musics
        notifyDataSetChanged()
    }

}