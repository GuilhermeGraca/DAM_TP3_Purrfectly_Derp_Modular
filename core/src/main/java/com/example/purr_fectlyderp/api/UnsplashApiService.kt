package com.example.purr_fectlyderp.api

import com.example.purr_fectlyderp.model.UnsplashSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("client_id") clientId: String
    ): UnsplashSearchResponse
}
