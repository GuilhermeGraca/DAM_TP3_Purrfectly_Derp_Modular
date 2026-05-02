package com.example.purr_fectlyderp.model

import com.google.gson.annotations.SerializedName

data class UnsplashSearchResponse(
    @SerializedName("total") val total: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("results") val results: List<UnsplashImage>
)

data class UnsplashImage(
    @SerializedName("id") val id: String,
    @SerializedName("description") val description: String?,
    @SerializedName("urls") val urls: UnsplashImageUrls,
    @SerializedName("user") val user: UnsplashUser
)

data class UnsplashImageUrls(
    @SerializedName("regular") val regular: String,
    @SerializedName("thumb") val thumb: String
)

data class UnsplashUser(
    @SerializedName("name") val name: String
)
