package com.example.purr_fectlyderp_compose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.purr_fectlyderp.model.UnsplashImage
import com.example.purr_fectlyderp_compose.viewmodel.MainViewModel
import com.example.purr_fectlyderp_compose.ui.theme.BgGradientStart
import com.example.purr_fectlyderp_compose.ui.theme.BgGradientEnd
import com.example.purr_fectlyderp_compose.ui.theme.ColorPrimaryTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DerpGridScreen(
    viewModel: MainViewModel,
    onNavigateToFavorites: () -> Unit
) {
    val images by viewModel.images.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    var selectedImage by remember { mutableStateOf<UnsplashImage?>(null) }

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(BgGradientStart, BgGradientEnd)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header Customizado igual ao XML
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 16.dp, start = 24.dp, end = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Friendly Purr-fectly\nDerp Gallery",
                    color = ColorPrimaryTitle,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 32.sp
                )
                
                Row {
                    IconButton(onClick = { viewModel.fetchDerpImages() }) {
                        Icon(
                            Icons.Default.Refresh, 
                            contentDescription = "Refresh",
                            tint = ColorPrimaryTitle
                        )
                    }
                    IconButton(onClick = onNavigateToFavorites) {
                        Icon(
                            Icons.Default.Favorite, 
                            contentDescription = "Hall of fame",
                            tint = ColorPrimaryTitle,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }

            // Grelha de imagens
            Box(modifier = Modifier.weight(1f)) {
                if (images.isNotEmpty()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(images, key = { it.id }) { image ->
                            DerpGridItem(image = image) {
                                selectedImage = image
                            }
                        }
                    }
                } else if (!isLoading && errorMessage == null) {
                    Text(
                        text = "Nenhuma imagem encontrada.",
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = ColorPrimaryTitle
                    )
                }

                errorMessage?.let { msg ->
                    if (images.isEmpty()) {
                        Column(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = msg, color = MaterialTheme.colorScheme.error)
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(onClick = { viewModel.fetchDerpImages() }) {
                                Text("Tentar Novamente")
                            }
                        }
                    }
                }
            }
        }
    }

    selectedImage?.let { img ->
        DerpOMeterDialog(
            image = img,
            onDismiss = { selectedImage = null },
            onSaveFavorite = { imageToSave, derpLevel ->
                viewModel.saveFavorite(imageToSave, derpLevel)
            }
        )
    }
}

@Composable
fun DerpGridItem(image: UnsplashImage, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        AsyncImage(
            model = image.urls.regular,
            contentDescription = image.description,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
