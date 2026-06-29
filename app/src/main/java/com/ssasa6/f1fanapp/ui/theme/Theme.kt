package com.ssasa6.f1fanapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PwColorScheme = darkColorScheme(
    primary          = TeamMcLaren,
    onPrimary        = Color.Black,
    background       = PwBg,
    onBackground     = PwText,
    surface          = PwCard,
    onSurface        = PwText,
    surfaceVariant   = PwSurface,
    onSurfaceVariant = PwTextSub,
)

@Composable
fun F1FanAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = PwColorScheme,
        typography  = Typography,
        content     = content
    )
}
