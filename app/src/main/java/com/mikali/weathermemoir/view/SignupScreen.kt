package com.mikali.weathermemoir.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mikali.weathermemoir.navigation.NavigationScreens

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(modifier = modifier) {
        // Create Account Button
        Button(onClick = { navController.navigate(route = NavigationScreens.MAIN.name) }) {
            Text(text = "Create Account Button on Signup screen")
        }

        // Back to Login screen Button
        Button(onClick = { navController.navigate(route = NavigationScreens.LOGIN.name) }) {
            Text(text = "back to login screen button")
        }
    }
}
