package com.mikali.weathermemoir.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mikali.weathermemoir.model.Weather
import com.mikali.weathermemoir.navigation.NavigationScreens
import com.mikali.weathermemoir.viewmodel.MainViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    navController: NavController
) {
    val state = viewModel.weatherObservable.subscribeAsState(initial = null).value
    val loadingState = viewModel.loadingObservable.subscribeAsState(initial = null).value

    if (loadingState == true) {
        CircularProgressIndicator()
    } else {
        MainScreenWithContent(modifier, navController, state)
    }
}

@Composable
private fun MainScreenWithContent(
    modifier: Modifier,
    navController: NavController,
    state: Weather?
) {
    Column(modifier = modifier) {
        // Login out Button
        Button(onClick = { navController.navigate(route = NavigationScreens.LOGIN.name) }) {
            Text(text = "Logout button on Main screen ${state?.name}")
        }
        // See All Button
        Button(onClick = { navController.navigate(route = NavigationScreens.MEMOIR_LIST.name) }) {
            Text(text = "See all button")
        }

        // Story icon
        val list = listOf<String>("1", "2", "3")
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(4.dp)

        ) {
            items(
                count = list.size,
                itemContent = {
                    Card(
                        modifier = Modifier.clickable {
                            navController.navigate(route = NavigationScreens.QUESTIONNAIRE.name)
                        },
                        elevation = 2.dp
                    ) {
                        Text(text = "TODO on MainScreen")
                    }
                }
            )
        }
    }
}
