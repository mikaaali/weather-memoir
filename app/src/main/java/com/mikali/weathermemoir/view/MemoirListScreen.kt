package com.mikali.weathermemoir.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mikali.weathermemoir.navigation.NavigationScreens

@Composable
fun MemoirListScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    LazyColumn(modifier = modifier) {
        items(
            items = listOf("1", "2", "3"),
            itemContent = {
                Text(
                    text = it + "MemoirListScreen",
                    modifier = Modifier.clickable {
                        navController.navigate(route = NavigationScreens.QUESTIONNAIRE.name)
                    }
                )
            }
        )
    }
}
