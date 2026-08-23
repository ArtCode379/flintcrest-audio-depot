package flintcresttrade.musical.flintcrestaudiodepot.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val FlintcrestColors = lightColorScheme(
    primary = BrandPrimary,
    onPrimary = BrandOnPrimary,
    secondary = BrandAccent,
    onSecondary = BrandOnPrimary,
    background = BrandBackground,
    onBackground = BrandOnSurface,
    surface = BrandSurface,
    onSurface = BrandOnSurface,
    surfaceVariant = BrandChipBackground,
    onSurfaceVariant = BrandMuted,
    outline = BrandBorder,
    error = Color(0xFFBA1A1A),
)

@Composable
fun ProductAppWHDBNTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = FlintcrestColors,
        typography = AppTypography,
        content = content,
    )
}
