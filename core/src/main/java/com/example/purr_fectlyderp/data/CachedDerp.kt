package com.example.purr_fectlyderp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_derps")
data class CachedDerp(
    @PrimaryKey val id: String,
    val description: String?,
    val thumbUrl: String,
    val regularUrl: String,
    val photographerName: String,
    val timestamp: Long
)
