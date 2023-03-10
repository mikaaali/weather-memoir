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
import com.mikali.weathermemoir.viewmodel.HomeViewModel
import com.mikali.weathermemoir.viewmodel.LoginViewModel
import com.mikali.weathermemoir.viewmodel.MainViewModel
import com.mikali.weathermemoir.viewmodel.MemoirListViewModel
import com.mikali.weathermemoir.viewmodel.QuestionnaireViewModel
import com.mikali.weathermemoir.viewmodel.SignupViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    // Lazy inject login screen ViewModel
    private val loginViewModel: LoginViewModel by viewModel()

    // Lazy inject signup screen ViewModel
    private val signupViewModel: SignupViewModel by viewModel()

    // Lazy inject home screen ViewModel
    private val homeViewModel: HomeViewModel by viewModel()

    // Lazy inject questionnaire ViewModel
    private val questionnaireViewModel: QuestionnaireViewModel by viewModel()

    // Lazy inject list ViewModel
    private val memoirListViewModel: MemoirListViewModel by viewModel()

    // Lazy inject main ViewModel
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherMemoirApp(
                loginViewModel = loginViewModel,
                signupViewModel = signupViewModel,
                homeViewModel = homeViewModel,
                questionnaireViewModel = questionnaireViewModel,
                memoirListViewModel = memoirListViewModel,
                mainViewModel = mainViewModel
            )
        }
    }

    override fun onStart() {
        super.onStart()
        homeViewModel.getWeather("39.9612", "-82.9988")
    }
}

@Composable
fun WeatherMemoirApp(
    loginViewModel: LoginViewModel,
    signupViewModel: SignupViewModel,
    homeViewModel: HomeViewModel,
    questionnaireViewModel: QuestionnaireViewModel,
    memoirListViewModel: MemoirListViewModel,
    mainViewModel: MainViewModel
) {
    WeatherMemoirTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Navigation(
                loginViewModel = loginViewModel,
                signupViewModel = signupViewModel,
                homeViewModel = homeViewModel,
                questionnaireViewModel = questionnaireViewModel,
                memoirListViewModel = memoirListViewModel,
                mainViewModel = mainViewModel
            )
        }
    }
}
