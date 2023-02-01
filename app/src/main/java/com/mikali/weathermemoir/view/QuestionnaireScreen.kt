package com.mikali.weathermemoir.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mikali.weathermemoir.navigation.NavigationScreens

@Composable
fun QuestionnaireScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(modifier = modifier) {
        // Back Button
        Button(onClick = { navController.navigate(route = NavigationScreens.MAIN.name) }) {
            Text(text = "Back button on QuestionnaireScreen")
        }
    }
}
