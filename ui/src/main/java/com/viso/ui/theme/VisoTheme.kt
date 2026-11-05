package com.viso.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object VisoTokens {
    val Background = Color(0xFF080B12)
    val Surface = Color(0xFF111827)
    val SurfaceRaised = Color(0xFF1A2435)
    val Primary = Color(0xFF8B7CFF)
    val Secondary = Color(0xFF45D6C5)
    val Accent = Color(0xFFFFB86B)
    val TextPrimary = Color(0xFFF4F5FA)
    val TextSecondary = Color(0xFFA5AEC0)
    val Divider = Color(0xFF293449)
}

private val VisoDarkColors = darkColorScheme(
    primary = VisoTokens.Primary,
    secondary = VisoTokens.Secondary,
    tertiary = VisoTokens.Accent,
    background = VisoTokens.Background,
    surface = VisoTokens.Surface,
    onPrimary = Color.White,
    onSecondary = Color(0xFF061311),
    onBackground = VisoTokens.TextPrimary,
    onSurface = VisoTokens.TextPrimary
)

@Composable
fun VisoTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = VisoDarkColors, content = content)
}
