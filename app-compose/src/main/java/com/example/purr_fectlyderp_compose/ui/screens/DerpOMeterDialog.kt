package com.example.purr_fectlyderp_compose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.purr_fectlyderp.model.UnsplashImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DerpOMeterDialog(
    image: UnsplashImage,
    onDismiss: () -> Unit,
    onSaveFavorite: (UnsplashImage, Int) -> Unit
) {
    var sliderValue by remember { mutableStateOf(50f) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        modifier = Modifier.fillMaxHeight(0.85f) // Sheet height
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Avalia o Nível de Derp!",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            AsyncImage(
                model = image.urls.regular,
                contentDescription = image.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = image.description ?: "Sem descrição disponível",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Nível de Derp: ${sliderValue.toInt()}%",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                valueRange = 0f..100f,
                steps = 100,
                modifier = Modifier.padding(vertical = 8.dp),
                colors = SliderDefaults.colors(
                    thumbColor = com.example.purr_fectlyderp_compose.ui.theme.ColorSliderActive,
                    activeTrackColor = com.example.purr_fectlyderp_compose.ui.theme.ColorSliderActive,
                    inactiveTrackColor = com.example.purr_fectlyderp_compose.ui.theme.ColorSliderInactive
                )
            )

            Button(
                onClick = { 
                    onSaveFavorite(image, sliderValue.toInt())
                    onDismiss()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = com.example.purr_fectlyderp_compose.ui.theme.ColorButtonBg,
                    contentColor = com.example.purr_fectlyderp_compose.ui.theme.ColorButtonText
                )
            ) {
                Text(text = "Adicionar ao Hall of Fame")
            }
        }
    }
}
