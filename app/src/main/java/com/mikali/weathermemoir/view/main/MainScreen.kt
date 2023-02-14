package com.mikali.weathermemoir.view.main

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.mikali.weathermemoir.view.home.HomeScreen
import com.mikali.weathermemoir.view.list.MemoirListScreen
import com.mikali.weathermemoir.view.questionnaire.QuestionnaireScreen
import com.mikali.weathermemoir.viewmodel.HomeViewModel

@Composable
fun MainScreen(
    homeViewModel: HomeViewModel
) {
    val bottomNavigationScreens = remember { mutableStateOf(BottomNavigationScreens.HOME) }
    Scaffold(
        bottomBar = { BottomBar(bottomNavigationScreens = bottomNavigationScreens) },
        content = {
            when (bottomNavigationScreens.value) {
                BottomNavigationScreens.HOME -> HomeScreen(viewModel = homeViewModel)
                BottomNavigationScreens.QUESTIONNAIRE -> QuestionnaireScreen()
                BottomNavigationScreens.MEMOIR_LIST -> MemoirListScreen()
            }
        }
    )
}