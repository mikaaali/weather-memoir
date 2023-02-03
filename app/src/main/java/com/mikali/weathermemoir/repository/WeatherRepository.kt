package com.mikali.weathermemoir.repository

import com.mikali.weathermemoir.model.Weather
import io.reactivex.Observable

interface WeatherRepository {
    fun getWeather(lat: String, lon: String): Observable<Weather>
}
