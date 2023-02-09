package com.mikali.weathermemoir.view.main

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun BottomBar(
    bottomNavigationScreens: MutableState<BottomNavigationScreens>
) {
    BottomNavigation() {
        BottomNavigationItem(
            selected = bottomNavigationScreens.value == BottomNavigationScreens.HOME,
            onClick = { bottomNavigationScreens.value = BottomNavigationScreens.HOME },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home Icon") },
            label = { Text(text = "Home") }
        )
        BottomNavigationItem(
            selected = bottomNavigationScreens.value == BottomNavigationScreens.QUESTIONNAIRE,
            onClick = { bottomNavigationScreens.value = BottomNavigationScreens.QUESTIONNAIRE },
            icon = { Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Icon") },
            label = { Text(text = "Record") }
        )
        BottomNavigationItem(
            selected = bottomNavigationScreens.value == BottomNavigationScreens.MEMOIR_LIST,
            onClick = { bottomNavigationScreens.value = BottomNavigationScreens.MEMOIR_LIST },
            icon = { Icon(imageVector = Icons.Default.List, contentDescription = "List Icon") },
            label = { Text(text = "Memoir List") }
        )
    }
}

enum class BottomNavigationScreens {
    HOME,
    QUESTIONNAIRE,
    MEMOIR_LIST
}
