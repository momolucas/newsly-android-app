package lucas.momo.newsly.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

internal val LightPrimary = Color(0xFF4A6572)
internal val LightOnPrimary = Color(0xFFFFFFFF)
internal val LightPrimaryContainer = Color(0xFFD9E4E7)
internal val LightOnPrimaryContainer = Color(0xFF102027)

internal val LightSecondary = Color(0xFF7B8D93)
internal val LightOnSecondary = Color(0xFFFFFFFF)

internal val LightBackground = Color(0xFFE8E8E8)
internal val LightOnBackground = Color(0xFF1A1A1A)

internal val LightSurface = Color(0xFFFFFFFF)
internal val LightOnSurface = Color(0xFF1A1A1A)

internal val LightError = Color(0xFFB00020)
internal val LightOnError = Color(0xFFFFFFFF)

internal val LightLink = Color(0xFF1A0DAB)

internal val DarkPrimary = Color(0xFFB0BEC5)
internal val DarkOnPrimary = Color(0xFF263238)
internal val DarkPrimaryContainer = Color(0xFF37474F)
internal val DarkOnPrimaryContainer = Color(0xFFECEFF1)

internal val DarkSecondary = Color(0xFF90A4AE)
internal val DarkOnSecondary = Color(0xFF263238)

internal val DarkBackground = Color(0xFF121212)
internal val DarkOnBackground = Color(0xFFE0E0E0)

internal val DarkSurface = Color(0xFF1E1E1E)
internal val DarkOnSurface = Color(0xFFE0E0E0)

internal val DarkError = Color(0xFFF2B8B5)
internal val DarkOnError = Color(0xFF601410)

internal val DarkLink = Color(0xFF8AB4F8)

internal val LightColorScheme =
    lightColorScheme(
        primary = LightPrimary,
        onPrimary = LightOnPrimary,
        primaryContainer = LightPrimaryContainer,
        onPrimaryContainer = LightOnPrimaryContainer,
        secondary = LightSecondary,
        onSecondary = LightOnSecondary,
        background = LightBackground,
        onBackground = LightOnBackground,
        surface = LightSurface,
        onSurface = LightOnSurface,
        error = LightError,
        onError = LightOnError,
        surfaceBright = LightLink,
    )

internal val DarkColorScheme =
    darkColorScheme(
        primary = DarkPrimary,
        onPrimary = DarkOnPrimary,
        primaryContainer = DarkPrimaryContainer,
        onPrimaryContainer = DarkOnPrimaryContainer,
        secondary = DarkSecondary,
        onSecondary = DarkOnSecondary,
        background = DarkBackground,
        onBackground = DarkOnBackground,
        surface = DarkSurface,
        onSurface = DarkOnSurface,
        error = DarkError,
        onError = DarkOnError,
        surfaceBright = DarkLink,
    )
