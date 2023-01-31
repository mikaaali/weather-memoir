package com.mikali.weathermemoir.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mikali.weathermemoir.view.LoginScreen
import com.mikali.weathermemoir.view.MainScreen
import com.mikali.weathermemoir.view.MemoirListScreen
import com.mikali.weathermemoir.view.QuestionnaireScreen
import com.mikali.weathermemoir.view.SignupScreen
import com.mikali.weathermemoir.view.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    // nav host holds all of the composable screens
    NavHost(
        navController = navController,
        startDestination = NavigationScreens.SPLASH.name
    ) {
        composable(
            route = NavigationScreens.SPLASH.name
        ) {
            SplashScreen(
                navController = navController
            )
            LoginScreen(
                navController = navController
            )
            SignupScreen(
                navController = navController
            )
            MainScreen(
                navController = navController
            )
            QuestionnaireScreen(
                navController = navController
            )
            MemoirListScreen(
                navController = navController
            )
        }
    }
}
