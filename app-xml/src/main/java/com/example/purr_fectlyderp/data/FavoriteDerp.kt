package com.example.purr_fectlyderp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteDerp(
    @PrimaryKey val id: String,
    val imageUrl: String,
    val photographerName: String,
    val description: String?,
    val derpLevel: Int,
    val timestamp: Long
)
