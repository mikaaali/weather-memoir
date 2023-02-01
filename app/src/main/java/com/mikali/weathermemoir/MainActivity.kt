package com.mikali.weathermemoir

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mikali.weathermemoir.navigation.Navigation
import com.mikali.weathermemoir.view.theme.WeatherMemoirTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherMemoirApp()
        }
    }
}

@Composable
fun WeatherMemoirApp() {
    WeatherMemoirTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Navigation()
        }
    }
}
