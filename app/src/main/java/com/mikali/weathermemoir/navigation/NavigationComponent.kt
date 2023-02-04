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
import com.mikali.weathermemoir.viewmodel.MainViewModel

@Composable
fun Navigation(
    mainViewModel: MainViewModel
) {
    val navController = rememberNavController()

    // nav host holds all of the composable screens
    NavHost(
        navController = navController,
        startDestination = NavigationScreens.SPLASH.name
    ) {
        composable(route = NavigationScreens.SPLASH.name) {
            SplashScreen(navController = navController)
        }
        composable(route = NavigationScreens.LOGIN.name) {
            LoginScreen(navController = navController)
        }
        composable(route = NavigationScreens.SIGNUP.name) {
            SignupScreen(navController = navController)
        }
        composable(route = NavigationScreens.MAIN.name) {
            MainScreen(viewModel = mainViewModel, navController = navController)
        }
        composable(route = NavigationScreens.QUESTIONNAIRE.name) {
            QuestionnaireScreen(navController = navController)
        }
        composable(route = NavigationScreens.MEMOIR_LIST.name) {
            MemoirListScreen(navController = navController)
        }
    }
}
