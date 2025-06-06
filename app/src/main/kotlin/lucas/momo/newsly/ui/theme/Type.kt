package lucas.momo.newsly.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

internal val Typography =
    Typography(
        headlineLarge =
            TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            ),
        headlineMedium =
            TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            ),
        bodyLarge =
            TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
            ),
        bodyMedium =
            TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
            ),
        labelSmall =
            TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
            ),
    )
