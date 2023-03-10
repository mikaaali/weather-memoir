package com.mikali.weathermemoir.di

import com.mikali.weathermemoir.repository.WeatherRepository
import com.mikali.weathermemoir.repository.WeatherRepositoryImpl
import com.mikali.weathermemoir.viewmodel.HomeViewModel
import com.mikali.weathermemoir.viewmodel.LoginViewModel
import com.mikali.weathermemoir.viewmodel.MainViewModel
import com.mikali.weathermemoir.viewmodel.MemoirListViewModel
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

    // define Login screen ViewModel instance
    viewModel {
        LoginViewModel(
            // firebaseAuth = get(),
            databaseRepository = get(),
            firestoreRepository = get()
        )
    }

    // define Signup screen ViewModel instance
    viewModel {
        SignupViewModel(
            // firebaseAuth = get(),
            databaseRepository = get()
        )
    }

    // define Questionnaire screen ViewModel instance
    viewModel {
        QuestionnaireViewModel(
            databaseRepository = get(),
            weatherRepository = get()
        )
    }

    // define List screen ViewModel instance
    viewModel {
        MemoirListViewModel(databaseRepository = get())
    }

    // define Main screen ViewModel instance
    viewModel {
        MainViewModel(
            databaseRepository = get(),
            firestoreRepository = get()
        )
    }
}
