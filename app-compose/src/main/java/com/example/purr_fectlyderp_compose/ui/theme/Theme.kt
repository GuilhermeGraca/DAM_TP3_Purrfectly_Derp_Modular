package com.example.purr_fectlyderp_compose.ui.theme

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Cores exatas do XML original para consistência visual no modo Claro
val BgGradientStart = Color(0xFFEBE5D2)
val BgGradientEnd = Color(0xFFD4C8E0)
val ColorPrimaryTitle = Color(0xFFD29662)
val ColorCardTitle = Color(0xFF49311C)
val ColorDerpText = Color(0xFFCC9D75)
val ColorSliderActive = Color(0xFFA18BC9)
val ColorSliderInactive = Color(0xFFDDD2F3)
val ColorButtonBg = Color(0xFFDB9059)
val ColorButtonText = Color(0xFF49311C)

private val DarkColorScheme = darkColorScheme(
    primary = ColorPrimaryTitle,
    secondary = ColorSliderActive,
    tertiary = ColorButtonBg,
    background = Color(0xFF1E1E1E),
    surface = Color(0xFF2C2C2C),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color(0xFFE0E0E0),
    onSurface = Color(0xFFE0E0E0)
)

private val LightColorScheme = lightColorScheme(
    primary = ColorPrimaryTitle,
    secondary = ColorSliderActive,
    tertiary = ColorButtonBg,
    background = BgGradientStart, // Base background, mas usaremos Brush no Compose
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = ColorCardTitle,
    onBackground = ColorCardTitle,
    onSurface = ColorCardTitle
)

tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@Composable
fun PurrfectlyDerpTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = view.context.findActivity()?.window
            if (window != null) {
                window.statusBarColor = colorScheme.primary.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
