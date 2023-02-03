package com.mikali.weathermemoir.di

import com.mikali.weathermemoir.repository.WeatherRepository
import com.mikali.weathermemoir.repository.WeatherRepositoryImpl
import org.koin.dsl.module

val appModule = module {

    single<WeatherRepository> {
        WeatherRepositoryImpl(
            weatherAPI = get()
        )
    }
}
