package com.example.purr_fectlyderp_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.purr_fectlyderp_compose.ui.theme.PurrfectlyDerpTheme
import com.example.purr_fectlyderp_compose.viewmodel.FavoritesViewModel
import com.example.purr_fectlyderp_compose.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    
    // ViewModels instanciados tal como na app XML, mas preparados com StateFlow
    private val mainViewModel: MainViewModel by viewModels()
    private val favoritesViewModel: FavoritesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PurrfectlyDerpTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlaceholderScreen()
                }
            }
        }
    }
}

@Composable
fun PlaceholderScreen() {
    Text(text = "Hello Compose! A arquitetura base está montada.")
}
