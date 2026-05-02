package com.example.purr_fectlyderp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.purr_fectlyderp.data.FavoriteDerp

class FavoritesAdapter(
    private val favorites: List<FavoriteDerp>
) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.favImageView)
        val photographerText: TextView = view.findViewById(R.id.favPhotographerText)
        val derpLevelText: TextView = view.findViewById(R.id.favDerpLevelText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fav = favorites[position]
        holder.photographerText.text = fav.photographerName
        holder.derpLevelText.text = "Nível de Derp: ${fav.derpLevel}%"
        
        Glide.with(holder.itemView.context)
            .load(fav.imageUrl)
            .centerCrop()
            .into(holder.imageView)
    }

    override fun getItemCount() = favorites.size
}
