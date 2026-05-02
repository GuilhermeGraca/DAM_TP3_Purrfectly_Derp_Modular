package com.example.purr_fectlyderp.repository

import com.example.purr_fectlyderp.api.RetrofitClient
import com.example.purr_fectlyderp.data.CachedDao
import com.example.purr_fectlyderp.data.CachedDerp
import com.example.purr_fectlyderp.model.UnsplashImage
import com.example.purr_fectlyderp.model.UnsplashImageUrls
import com.example.purr_fectlyderp.model.UnsplashUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UnsplashRepository(private val cachedDao: CachedDao) {
    private val apiService = RetrofitClient.apiService

    suspend fun getDerpImages(query: String, perPage: Int, page: Int, clientId: String): Pair<List<UnsplashImage>, Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                // Tenta puxar da rede
                val response = apiService.searchPhotos(query, perPage, page, clientId)
                val images = response.results
                
                // Converte em CachedDerp para guardar
                if (images.isNotEmpty()) {
                    val cachedList = images.map { img ->
                        CachedDerp(
                            id = img.id,
                            description = img.description,
                            thumbUrl = img.urls.thumb,
                            regularUrl = img.urls.regular,
                            photographerName = img.user?.name ?: "Desconhecido",
                            timestamp = System.currentTimeMillis()
                        )
                    }
                    cachedDao.insertAll(cachedList)
                    // Limpar antigos (mantém top 50)
                    cachedDao.deleteOldestBeyondLimit()
                }
                
                Pair(images, false) // false = Not cached, it's fresh
            } catch (e: Exception) {
                // Em caso de falha de rede (ex: IOException) vai buscar à Base de Dados
                val cached = cachedDao.getAllCached()
                val convertedImages = cached.map { c ->
                    UnsplashImage(
                        id = c.id,
                        description = c.description,
                        urls = UnsplashImageUrls(regular = c.regularUrl, thumb = c.thumbUrl),
                        user = UnsplashUser(name = c.photographerName)
                    )
                }
                Pair(convertedImages, true) // true = It's from cache
            }
        }
    }
}
