package com.mikali.weathermemoir.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mikali.weathermemoir.navigation.NavigationScreens

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(modifier = modifier) {
        // Sign In Button
        Button(onClick = { navController.navigate(route = NavigationScreens.MAIN.name) }) {
            Text(text = "Sign in Button on Login screen")
        }
        // Create an account button
        Button(onClick = { navController.navigate(route = NavigationScreens.SIGNUP.name) }) {
            Text(text = "Create an account button on Login screen")
        }
    }
}
