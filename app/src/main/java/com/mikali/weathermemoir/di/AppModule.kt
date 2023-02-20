package com.mikali.weathermemoir.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mikali.weathermemoir.repository.WeatherRepository
import com.mikali.weathermemoir.repository.WeatherRepositoryImpl
import com.mikali.weathermemoir.viewmodel.HomeViewModel
import com.mikali.weathermemoir.viewmodel.LoginViewModel
import com.mikali.weathermemoir.viewmodel.QuestionnaireViewModel
import com.mikali.weathermemoir.viewmodel.SignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // define weatherRepository instance
    single<WeatherRepository> {
        WeatherRepositoryImpl(
            weatherAPI = get()
        )
    }

    // define Home screen ViewModel instance
    viewModel {
        HomeViewModel(
            weatherRepository = get()
        )
    }

    // create an instance of FirebaseAuth, so we can get access to the firebase auth tool
    // single will create only one class, no matter how many times it is called
    // so this means login and signup share the same instance
    single<FirebaseAuth> {
        Firebase.auth
    }

    // define Login screen ViewModel instance
    viewModel {
        LoginViewModel(
            firebaseAuth = get()
        )
    }

    // define Signup screen ViewModel instance
    viewModel {
        SignupViewModel(
            firebaseAuth = get()
        )
    }

    // define Questionnaire screen ViewModel instance
    viewModel {
        QuestionnaireViewModel()
    }
}
