package com.mikali.weathermemoir.view.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mikali.weathermemoir.view.theme.SuperLightGreen

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun MemoirListScreen(
    listOfAnswers: List<String> = listOf("1", "2", "3")
) {
    // want to gain control of the keyboard, so we can hide the keyboard when needed
    val keyboardController = LocalSoftwareKeyboardController.current
    val query = remember { mutableStateOf("") }

    Column() {
        // Search Field
        OutlinedTextField(
            value = query.value,
            onValueChange = {
                query.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            label = { Text(text = "Weather(ex. Mist, Sky, etc.)") },
            trailingIcon = {
                Icon(
                    imageVector = if (query.value.isNotEmpty()) Icons.Default.Clear else Icons.Default.Search,
                    contentDescription = "Search Box Trailing Icon"
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                }
            ),
            singleLine = true,
            maxLines = 1,
            shape = CircleShape
        )

        // Grid List
        LazyVerticalGrid(
            cells = GridCells.Fixed(2)
        ) {
            items(count = listOfAnswers.size) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(SuperLightGreen)
                        .clickable(onClick = {}),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        imageVector = Icons.Outlined.WbSunny,
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(60.dp)
                            .align(Alignment.Top)
                    )
                    Text(
                        text = "Feb 8, 2023",
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}
