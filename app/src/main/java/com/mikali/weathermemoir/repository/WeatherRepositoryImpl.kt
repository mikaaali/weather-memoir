package com.mikali.weathermemoir.repository

import com.mikali.weathermemoir.model.Weather
import com.mikali.weathermemoir.network.WeatherAPI
import io.reactivex.Observable

class WeatherRepositoryImpl(
    private val weatherAPI: WeatherAPI
) : WeatherRepository {
    override fun getWeather(lat: String, lon: String): Observable<Weather> =
        weatherAPI.getWeather(lat = lat, lon = lon)
}
