package com.mikali.weathermemoir.view.home

import HomeBackLayerContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mikali.weathermemoir.view.theme.SuperLightGreen
import com.mikali.weathermemoir.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val state = viewModel.homeObservable.subscribeAsState(initial = null).value
    val loadingState = viewModel.loadingObservable.subscribeAsState(initial = null).value

    if (loadingState == true) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .align(Alignment.Center),
                strokeWidth = 16.dp
            )
        }
    } else {
        BackdropScaffold(
            frontLayerScrimColor = Color.Unspecified,
            appBar = { /*No Operation*/ },
            backLayerContent = {
                if (state != null) {
                    HomeBackLayerContent(state.weather)
                }
            },
            frontLayerContent = {
                Column() {
                    Text(
                        text = "Latest Thoughts",
                        style = typography.h5,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    if (state != null) {
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(SuperLightGreen)
                                .clickable { }
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = "https://openweathermap.org/img/wn/${state.memoir.mainWeatherConditionIcon}.png"
                                ),
                                contentDescription = "Weather Icon",
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(60.dp)
                                    .align(Alignment.Top)
                            )
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(
                                    text = state.memoir.question,
                                    style = typography.subtitle1,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = state.memoir.creationTime,
                                    style = typography.subtitle2,
                                    color = Color.Gray
                                )
                                Text(
                                    text = state.memoir.thought,
                                    style = typography.body2,
                                    maxLines = 5,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}
