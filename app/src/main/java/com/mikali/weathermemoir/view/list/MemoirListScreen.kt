package com.mikali.weathermemoir.view.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mikali.weathermemoir.view.main.BottomNavigationScreens
import com.mikali.weathermemoir.viewmodel.MemoirListViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MemoirListScreen(
    viewModel: MemoirListViewModel,
    bottomNavigationScreens: MutableState<BottomNavigationScreens>
) {
    // want to gain control of the keyboard, so we can hide the keyboard when needed
    val keyboardController = LocalSoftwareKeyboardController.current
    val state = viewModel.listObservable.subscribeAsState(initial = null).value

    Column() {
        // Search Field
        OutlinedTextField(
            value = state?.searchQuery ?: "",
            onValueChange = {
                viewModel.onSearchValueChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            label = { Text(text = "Search(ex. Mist, Sky, etc.)") },
            trailingIcon = {
                Icon(
                    imageVector = if (state?.searchQuery?.isNotEmpty() == true) {
                        Icons.Default.Clear
                    } else {
                        Icons.Default.Search
                    },
                    contentDescription = "Search Box Trailing Icon",
                    modifier = Modifier.clickable(
                        indication = rememberRipple(bounded = false),
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            if (state?.searchQuery?.isNotEmpty() == true) viewModel.onClearSearchQuery()
                        }
                    )
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    viewModel.getThoughtEntries(state?.searchQuery)
                    keyboardController?.hide()
                }
            ),
            singleLine = true,
            maxLines = 1,
            shape = CircleShape
        )
        if (state != null) {
            if (state.searchQuery.isNotBlank() && state.listOfAnswers.isEmpty()) {
                Text(
                    text = "Sorry, No Result found :(",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                GridList(state, bottomNavigationScreens)
            }
        }
    }
}
