package com.qdroid.users.ui.navigation

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.qdroid.users.ui.screens.UserDetailsScreen
import com.qdroid.users.ui.screens.UsersScreen
import com.qdroid.users.viewmodel.UserDetailsViewModel
import com.qdroid.users.viewmodel.UsersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val usersViewModel: UsersViewModel = viewModel()
    val userDetailsViewModel: UserDetailsViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Users.route) {
        composable(route = Screen.Users.route) {
            UsersScreen(navController = navController, viewModel = usersViewModel)
        }
        composable(route = Screen.UserDetails.route) {
            val userId =it.arguments?.getString("userId")
            Log.d("Navigation", "Navigation: $userId")
            UserDetailsScreen(
                navController = navController,
                viewModel = userDetailsViewModel,
                userId = Integer.parseInt(userId)
            )
        }
    }
}