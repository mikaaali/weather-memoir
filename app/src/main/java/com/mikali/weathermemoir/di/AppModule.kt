package com.mikali.weathermemoir.di

import com.mikali.weathermemoir.repository.WeatherRepository
import com.mikali.weathermemoir.repository.WeatherRepositoryImpl
import com.mikali.weathermemoir.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // define weatherRepository instance
    single<WeatherRepository> {
        WeatherRepositoryImpl(
            weatherAPI = get()
        )
    }

    // define MainViewModel instance
    viewModel {
        MainViewModel(
            weatherRepository = get()
        )
    }
}
