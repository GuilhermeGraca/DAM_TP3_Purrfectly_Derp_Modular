package com.example.purr_fectlyderp_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import com.example.purr_fectlyderp_compose.ui.theme.PurrfectlyDerpTheme
import com.example.purr_fectlyderp_compose.viewmodel.FavoritesViewModel
import com.example.purr_fectlyderp_compose.viewmodel.MainViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.purr_fectlyderp_compose.ui.screens.DerpGridScreen
import com.example.purr_fectlyderp_compose.ui.screens.FavoritesScreen
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

enum class Screen {
    Grid,
    Favorites
}



class MainActivity : ComponentActivity() {
    
    private val mainViewModel: MainViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }
    private val favoritesViewModel: FavoritesViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemDarkTheme = isSystemInDarkTheme()
            var isDarkTheme by remember { mutableStateOf(systemDarkTheme) }
            
            PurrfectlyDerpTheme(darkTheme = isDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val pagerState = rememberPagerState(pageCount = { 2 })
                    val coroutineScope = rememberCoroutineScope()

                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxSize()
                    ) { page ->
                        when (page) {
                            0 -> {
                                DerpGridScreen(
                                    viewModel = mainViewModel,
                                    onNavigateToFavorites = { 
                                        coroutineScope.launch { pagerState.animateScrollToPage(1) } 
                                    },
                                    isDarkTheme = isDarkTheme,
                                    onToggleTheme = { isDarkTheme = !isDarkTheme }
                                )
                            }
                            1 -> {
                                FavoritesScreen(
                                    viewModel = favoritesViewModel,
                                    onNavigateBack = { 
                                        coroutineScope.launch { pagerState.animateScrollToPage(0) } 
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
