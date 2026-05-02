package com.example.purr_fectlyderp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.purr_fectlyderp.data.AppDatabase
import com.example.purr_fectlyderp.data.FavoriteDerp
import com.example.purr_fectlyderp.model.UnsplashImage
import com.example.purr_fectlyderp.repository.UnsplashRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val appDb by lazy { AppDatabase.getDatabase(application) }
    private val repository by lazy { UnsplashRepository(appDb.cachedDao()) }
    private val favoriteDao by lazy { appDb.favoriteDao() }

    private val _images = MutableLiveData<List<UnsplashImage>>()
    val images: LiveData<List<UnsplashImage>> = _images

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

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
                
                // Se o mix de Palavras vs Página resultar numa lista vazia ONLINE, faz fallback para a Página 1
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
                
                // Se MESMO na página 1 estiver vazio, tentar outra keyword garantida
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
                // Erros genericos nao rede
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
