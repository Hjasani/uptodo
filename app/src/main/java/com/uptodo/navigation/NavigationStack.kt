package com.uptodo.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uptodo.ui.auth.login.AuthRoute
import com.uptodo.ui.auth.onboarding.OnboardingRoute


@Composable
fun NavigationStack() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Onboarding.route) {
        composable(route = Screens.Onboarding.route) {
            OnboardingRoute(
                Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {
                navController.navigate(Screens.Auth.route) {
                    popUpTo(Screens.Onboarding.route) {
                        inclusive = true
                    }
                }
            }
        }

        composable(route = Screens.Auth.route) {
            AuthRoute(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding(),
                onLoginClick = {},
                onCreateAccountClick = {}
            )
        }
    }
}