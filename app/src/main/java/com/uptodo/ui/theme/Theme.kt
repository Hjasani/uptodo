package com.uptodo.ui.theme

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.LocalActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowInsetsControllerCompat

private val DarkColorScheme = darkColorScheme(
    primary = AppColor.Purple,
    secondary = AppColor.Grey,
    tertiary = AppColor.Purple,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = AppColor.Purple,
    secondary = AppColor.Grey,
    tertiary = AppColor.Purple,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onTertiary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@Composable
fun UpTodoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
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

    // Configure proper colors for visibility
    val statusBarLight: Int = Color.Black.toArgb()  // Light background
    val statusBarDark: Int = Color.Black.toArgb()   // Dark background
    val navigationBarLight: Int = Color.Black.toArgb()
    val navigationBarDark: Int = Color.Black.toArgb()

    // Use LocalActivity instead of casting LocalContext
    val activity = LocalActivity.current as ComponentActivity

    SideEffect {
        val window = activity.window

        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.isAppearanceLightStatusBars = false  // false = white icons
        controller.isAppearanceLightNavigationBars = false
    }

    DisposableEffect(darkTheme) {
        activity.enableEdgeToEdge(
            statusBarStyle = if (darkTheme) {
                // Dark theme: black background with light (white) icons
                SystemBarStyle.dark(statusBarDark)
            } else {
                // Light theme: light background with dark icons
                SystemBarStyle.light(
                    statusBarLight,
                    statusBarDark
                )
            },
            navigationBarStyle = if (darkTheme) {
                // Dark theme: black background with light icons
                SystemBarStyle.dark(navigationBarDark)
            } else {
                // Light theme: light background with dark icons
                SystemBarStyle.light(
                    navigationBarLight,
                    navigationBarDark
                )
            }
        )

        onDispose { }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
