package com.example.purr_fectlyderp_compose.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.purr_fectlyderp.data.AppDatabase
import com.example.purr_fectlyderp.data.FavoriteDerp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val favoriteDao by lazy { AppDatabase.getDatabase(application).favoriteDao() }

    val favorites = favoriteDao.getAllFavorites()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
