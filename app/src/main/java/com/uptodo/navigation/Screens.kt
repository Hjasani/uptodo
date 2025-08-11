package com.uptodo.navigation

sealed class Screens (val route: String) {
    object Onboarding : Screens("onboarding_screen")
    object Auth : Screens("auth_screen")
}