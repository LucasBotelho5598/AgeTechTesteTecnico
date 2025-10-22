package com.example.agetech.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agetech.Screen
import com.example.agetech.view.LoginUI
import com.example.agetech.view.previewPayments

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun AgeTechNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginUI(
                onLogin = {
                    navController.navigate(Screen.Payments.route)
                })
        }

        composable(route = Screen.Payments.route) {
            previewPayments(navController = navController)
        }
    }
}