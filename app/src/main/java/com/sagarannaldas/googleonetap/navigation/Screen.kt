package com.sagarannaldas.googleonetap.navigation

sealed class Screen(val route: String) {
    data object Login: Screen(route = "login_screen")

    data object Profile: Screen(route = "profile_screen")
}