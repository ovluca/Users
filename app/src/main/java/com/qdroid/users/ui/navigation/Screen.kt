package com.qdroid.users.ui.navigation

sealed class Screen(val route: String) {
    object Users : Screen(route = "user")
    object UserDetails : Screen(route = "user_details/{userId}")
}
