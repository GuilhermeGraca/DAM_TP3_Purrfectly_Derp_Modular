package com.example.purr_fectlyderp_compose.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.purr_fectlyderp.data.AppDatabase
import com.example.purr_fectlyderp.data.FavoriteDerp
import com.example.purr_fectlyderp.model.UnsplashImage
import com.example.purr_fectlyderp.repository.UnsplashRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val appDb by lazy { AppDatabase.getDatabase(application) }
    private val repository by lazy { UnsplashRepository(appDb.cachedDao()) }
    private val favoriteDao by lazy { appDb.favoriteDao() }

    private val _images = MutableStateFlow<List<UnsplashImage>>(emptyList())
    val images: StateFlow<List<UnsplashImage>> = _images.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val keywords = listOf("funny dog", "derp cat", "silly pet", "awkward animal", "funny cat", "derpy dog", "goofy pet", "lazy cat", "funny parrot")

    init {
        fetchDerpImages()
    }

    fun fetchDerpImages() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val randomQuery = keywords.random()
                val randomPage = (1..5).random()
            
                var (images, isFromCache) = repository.getDerpImages(
                    query = randomQuery,
                    perPage = 30,
                    page = randomPage,
                    clientId = "YuMfJz8sy0q6TSHgHqu9YIYkcAp45ydB0731Nsh4l9U"
                )
                
                if (images.isEmpty() && randomPage > 1 && !isFromCache) {
                    val fallbackResult = repository.getDerpImages(
                        query = randomQuery,
                        perPage = 30,
                        page = 1,
                        clientId = "YuMfJz8sy0q6TSHgHqu9YIYkcAp45ydB0731Nsh4l9U"
                    )
                    images = fallbackResult.first
                    isFromCache = fallbackResult.second
                }
                
                if (images.isEmpty() && !isFromCache) {
                    val fallbackQuery = keywords.first { it != randomQuery }
                    val finalFallbackResult = repository.getDerpImages(
                        query = fallbackQuery,
                        perPage = 30,
                        page = 1,
                        clientId = "YuMfJz8sy0q6TSHgHqu9YIYkcAp45ydB0731Nsh4l9U"
                    )
                    images = finalFallbackResult.first
                    isFromCache = finalFallbackResult.second
                }
                
                if (images.isNotEmpty()) {
                    _images.value = images
                    if (isFromCache) {
                        _errorMessage.value = "Modo Offline - A exibir Grelha Local"
                    }
                } else {
                    if (isFromCache) {
                        _errorMessage.value = "Sem ligação e sem Derps guardados offline :("
                    } else {
                        _errorMessage.value = "Não encontrámos imagens. Tenta novamente."
                    }
                }
            } catch (e: Exception) {
                _errorMessage.value = "Ocorreu um erro inesperado."
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun saveFavorite(image: UnsplashImage, derpLevel: Int) {
        viewModelScope.launch {
            val photographer = image.user?.name ?: "Desconhecido"
            val favorite = FavoriteDerp(
                id = image.id,
                imageUrl = image.urls.regular,
                photographerName = photographer,
                description = image.description,
                derpLevel = derpLevel,
                timestamp = System.currentTimeMillis()
            )
            withContext(Dispatchers.IO) {
                favoriteDao.insertFavorite(favorite)
                val count = favoriteDao.getCount()
                if (count > 5) {
                    favoriteDao.deleteOldestFavorite()
                }
            }
        }
    }
}
