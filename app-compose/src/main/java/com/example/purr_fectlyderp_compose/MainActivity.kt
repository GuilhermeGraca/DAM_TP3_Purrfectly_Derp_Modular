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
                    var currentScreen by remember { mutableStateOf(Screen.Grid) }

                    AnimatedContent(
                        targetState = currentScreen,
                        transitionSpec = {
                            if (targetState == Screen.Favorites) {
                                slideInHorizontally(animationSpec = tween(400), initialOffsetX = { fullWidth -> fullWidth }) + fadeIn() togetherWith
                                slideOutHorizontally(animationSpec = tween(400), targetOffsetX = { fullWidth -> -fullWidth }) + fadeOut()
                            } else {
                                slideInHorizontally(animationSpec = tween(400), initialOffsetX = { fullWidth -> -fullWidth }) + fadeIn() togetherWith
                                slideOutHorizontally(animationSpec = tween(400), targetOffsetX = { fullWidth -> fullWidth }) + fadeOut()
                            }
                        },
                        label = "ScreenTransition"
                    ) { targetScreen ->
                        when (targetScreen) {
                            Screen.Grid -> {
                                DerpGridScreen(
                                    viewModel = mainViewModel,
                                    onNavigateToFavorites = { currentScreen = Screen.Favorites },
                                    isDarkTheme = isDarkTheme,
                                    onToggleTheme = { isDarkTheme = !isDarkTheme }
                                )
                            }
                            Screen.Favorites -> {
                                FavoritesScreen(
                                    viewModel = favoritesViewModel,
                                    onNavigateBack = { currentScreen = Screen.Grid }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
