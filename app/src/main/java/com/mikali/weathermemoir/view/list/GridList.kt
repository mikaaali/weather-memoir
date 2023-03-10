package com.mikali.weathermemoir.view.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mikali.weathermemoir.view.main.BottomNavigationScreens
import com.mikali.weathermemoir.view.theme.SuperLightGreen
import com.mikali.weathermemoir.viewmodel.MemoirListViewModel

@Composable
fun GridList(
    state: MemoirListViewModel.MutableState,
    bottomNavigationScreens: MutableState<BottomNavigationScreens>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(items = state.listOfAnswers) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .height(100.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(SuperLightGreen)
                    .clickable(onClick = {
                        bottomNavigationScreens.value =
                            BottomNavigationScreens.QUESTIONNAIRE
                    }),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = "https://openweathermap.org/img/wn/${it.mainWeatherConditionIcon}.png"
                    ),
                    contentDescription = "Weather Icon",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(60.dp)
                        .align(Alignment.Top)
                )
                Text(
                    text = it.creationTime,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(8.dp),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}
