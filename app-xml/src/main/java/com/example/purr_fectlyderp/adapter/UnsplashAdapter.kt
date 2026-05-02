package com.example.purr_fectlyderp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.purr_fectlyderp.R
import com.example.purr_fectlyderp.model.UnsplashImage

class UnsplashAdapter(
    private var images: List<UnsplashImage>,
    private val onItemClick: (UnsplashImage) -> Unit
) : RecyclerView.Adapter<UnsplashAdapter.UnsplashViewHolder>() {

    class UnsplashViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewDerp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnsplashViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_unsplash_image, parent, false)
        return UnsplashViewHolder(view)
    }

    override fun onBindViewHolder(holder: UnsplashViewHolder, position: Int) {
        val image = images[position]
        Glide.with(holder.itemView.context)
            .load(image.urls.thumb)
            .centerCrop()
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            onItemClick(image)
        }
    }

    override fun getItemCount(): Int = images.size

    fun submitList(newImages: List<UnsplashImage>) {
        images = newImages
        notifyDataSetChanged()
    }
}
