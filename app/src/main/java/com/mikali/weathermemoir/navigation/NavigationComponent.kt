package com.mikali.weathermemoir.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mikali.weathermemoir.view.login.LoginScreen
import com.mikali.weathermemoir.view.main.MainScreen
import com.mikali.weathermemoir.view.signup.SignupScreen
import com.mikali.weathermemoir.view.splash.SplashScreen
import com.mikali.weathermemoir.viewmodel.HomeViewModel
import com.mikali.weathermemoir.viewmodel.LoginViewModel
import com.mikali.weathermemoir.viewmodel.MainViewModel
import com.mikali.weathermemoir.viewmodel.MemoirListViewModel
import com.mikali.weathermemoir.viewmodel.QuestionnaireViewModel
import com.mikali.weathermemoir.viewmodel.SignupViewModel

@Composable
fun Navigation(
    loginViewModel: LoginViewModel,
    signupViewModel: SignupViewModel,
    homeViewModel: HomeViewModel,
    questionnaireViewModel: QuestionnaireViewModel,
    memoirListViewModel: MemoirListViewModel,
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
            LoginScreen(
                viewModel = loginViewModel,
                navController = navController
            )
        }
        composable(route = NavigationScreens.SIGNUP.name) {
            SignupScreen(viewModel = signupViewModel, navController = navController)
        }
        composable(route = NavigationScreens.MAIN.name) {
            MainScreen(
                homeViewModel = homeViewModel,
                questionnaireViewModel = questionnaireViewModel,
                memoirListViewModel = memoirListViewModel,
                mainViewModel = mainViewModel,
                navController = navController
            )
        }
    }
}
