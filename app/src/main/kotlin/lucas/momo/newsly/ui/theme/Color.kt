package lucas.momo.newsly.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val LightPrimary = Color(0xFF4A6572)
val LightOnPrimary = Color(0xFFFFFFFF)
val LightPrimaryContainer = Color(0xFFD9E4E7)
val LightOnPrimaryContainer = Color(0xFF102027)

val LightSecondary = Color(0xFF7B8D93)
val LightOnSecondary = Color(0xFFFFFFFF)

val LightBackground = Color(0xFFE8E8E8)
val LightOnBackground = Color(0xFF1A1A1A)

val LightSurface = Color(0xFFFFFFFF)
val LightOnSurface = Color(0xFF1A1A1A)

val LightError = Color(0xFFB00020)
val LightOnError = Color(0xFFFFFFFF)

val DarkPrimary = Color(0xFFB0BEC5)
val DarkOnPrimary = Color(0xFF263238)
val DarkPrimaryContainer = Color(0xFF37474F)
val DarkOnPrimaryContainer = Color(0xFFECEFF1)

val DarkSecondary = Color(0xFF90A4AE)
val DarkOnSecondary = Color(0xFF263238)

val DarkBackground = Color(0xFF121212)
val DarkOnBackground = Color(0xFFE0E0E0)

val DarkSurface = Color(0xFF1E1E1E)
val DarkOnSurface = Color(0xFFE0E0E0)

val DarkError = Color(0xFFF2B8B5)
val DarkOnError = Color(0xFF601410)

val LightColorScheme =
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
    )

val DarkColorScheme =
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
    )
