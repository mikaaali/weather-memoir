import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.outlined.AcUnit
import androidx.compose.material.icons.outlined.Air
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mikali.weathermemoir.R
import com.mikali.weathermemoir.model.Weather

@Composable
fun HomeBackLayerContent(state: Weather) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${state.name} , ${state.sys?.country}",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        state.weather?.get(0)?.let { weatherCondition ->
            Row() {
                weatherCondition.icon?.let {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = "https://openweathermap.org/img/wn/$it.png"
                        ),
                        contentDescription = "icon image",
                        modifier = Modifier.size(60.dp)
                    )
                }

                Column() {
                    Text(
                        text = weatherCondition.main,
                        style = MaterialTheme.typography.subtitle1
                    )
                    Text(
                        text = weatherCondition.description,
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }

        state.main?.let { main ->
            Text(
                text = main.temp,
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = main.maxMinTemp
            )

            Text(
                text = main.feelsLike,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Row(modifier = Modifier.padding(4.dp)) {
                    Icon(
                        imageVector = Icons.Outlined.Air,
                        contentDescription = "Air Pressure Icon",
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.Bottom)
                    )
                    Text(
                        text = main.pressure
                    )
                }
                Row(modifier = Modifier.padding(4.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.humidity_percentage),
                        contentDescription = "Humidity Percentage Icon",
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.Bottom)
                    )
                    Text(
                        text = main.humidity
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier.padding(4.dp)) {
                Icon(
                    imageVector = Icons.Default.Visibility,
                    contentDescription = "Visibility Icon",
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.Bottom)
                )
                Text(
                    text = state.visibility
                )
            }
            Row(modifier = Modifier.padding(4.dp)) {
                Icon(
                    imageVector = Icons.Outlined.Cloud,
                    contentDescription = "Cloud Icon",
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.Bottom)
                )
                Text(
                    text = state.clouds?.all ?: "N/A"
                )
            }
        }

        state.rain?.let { rain ->
            Row(modifier = Modifier.padding(4.dp)) {
                Icon(
                    imageVector = Icons.Outlined.WaterDrop,
                    contentDescription = "Rain Icon",
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.Bottom)
                )
                Text(
                    text = rain.oneHour
                )
            }
            Row(modifier = Modifier.padding(4.dp)) {
                Icon(
                    imageVector = Icons.Outlined.WaterDrop,
                    contentDescription = "Rain Icon",
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.Bottom)
                )
                Text(
                    text = rain.threeHours
                )
            }
        }

        state.snow?.let { snow ->
            Row(modifier = Modifier.padding(4.dp)) {
                Icon(
                    imageVector = Icons.Outlined.AcUnit,
                    contentDescription = "Snow Icon",
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.Bottom)
                )
                Text(
                    text = snow.oneHour
                )
            }
            Row(modifier = Modifier.padding(4.dp)) {
                Icon(
                    imageVector = Icons.Outlined.AcUnit,
                    contentDescription = "Snow Icon",
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.Bottom)
                )
                Text(
                    text = snow.threeHours
                )
            }
        }

        state.wind?.let { wind ->
            Text(
                text = wind.value,
                style = MaterialTheme.typography.caption
            )
        }
    }
}
