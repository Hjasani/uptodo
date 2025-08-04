package com.uptodo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.uptodo.navigation.NavigationStack
import com.uptodo.ui.theme.UpTodoTheme
import com.uptodo.util.SPLASH_SCREEN_DELAY
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private var isSplashScreenVisible  = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSplashScreen()
        enableEdgeToEdge()
        setContent {
            UpTodoTheme {
                NavigationStack()
            }
        }
    }

    private fun setupSplashScreen() {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { isSplashScreenVisible }

        lifecycleScope.launch {
            delay(SPLASH_SCREEN_DELAY)
            isSplashScreenVisible = false
        }
    }
}