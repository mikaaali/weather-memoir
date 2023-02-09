package com.mikali.weathermemoir

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.firebase.firestore.FirebaseFirestore
import com.mikali.weathermemoir.navigation.Navigation
import com.mikali.weathermemoir.view.theme.WeatherMemoirTheme
import com.mikali.weathermemoir.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    // Lazy inject home screen ViewModel
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val database = FirebaseFirestore.getInstance()
            val user: MutableMap<String, Any> = HashMap()
            user["username"] = "usernameTest"
            user["password"] = "1234Test"

            // create a table in cloud firestore and add an entry
            database.collection("user account").add(user)
            WeatherMemoirApp(
                homeViewModel = homeViewModel
            )
        }
    }

    override fun onStart() {
        super.onStart()
        homeViewModel.getWeather("35.4676", "-97.508469")
    }
}

@Composable
fun WeatherMemoirApp(
    homeViewModel: HomeViewModel
) {
    WeatherMemoirTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Navigation(
                homeViewModel = homeViewModel
            )
        }
    }
}
