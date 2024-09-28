package com.suatzengin.cleannavigation.navigation

sealed class Screen(val route: String) {
    data object Home : Screen(route = "home")
    data object Search : Screen(route = "search")
    data object Notification : Screen(route = "notification")
    data object Profile : Screen(route = "profile")
    data object Settings : Screen(route = "settings")
}