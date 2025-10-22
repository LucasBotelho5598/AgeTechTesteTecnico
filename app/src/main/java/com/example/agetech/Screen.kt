package com.example.agetech

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument




sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Payments : Screen("payments")
}