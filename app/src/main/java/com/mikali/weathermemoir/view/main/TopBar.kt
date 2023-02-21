package com.mikali.weathermemoir.view.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.mikali.weathermemoir.navigation.NavigationScreens

@Composable
fun TopBar(navController: NavController) {
    TopAppBar(
        title = { Text(text = "WeatherMemoir") },
        actions = {
            IconButton(
                onClick = {
                    FirebaseAuth.getInstance().signOut().run {
                        navController.navigate(NavigationScreens.LOGIN.name)
                    }
                },
                content = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(imageVector = Icons.Filled.Logout, contentDescription = "Logout Icon")
                        Text(text = "Logout")
                    }
                }
            )
        },
        backgroundColor = Color.White
    )
}
