package com.example.amexbenefittracker.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = AmexAccentBlue,
    secondary = AmexCardBlue,
    tertiary = AmexProfitGreen,
    background = AmexDarkBlue,
    surface = AmexCardBlue,
    onPrimary = AmexTextWhite,
    onSecondary = AmexTextWhite,
    onTertiary = AmexTextWhite,
    onBackground = AmexTextWhite,
    onSurface = AmexTextWhite,
)

@Composable
fun AmexBenefitTrackerTheme(
    darkTheme: Boolean = true, // Force dark theme by default to match Amex aesthetic
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
