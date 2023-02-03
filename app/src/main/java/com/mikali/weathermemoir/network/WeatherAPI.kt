package com.mikali.weathermemoir.network

import com.mikali.weathermemoir.model.Weather
import com.mikali.weathermemoir.util.Constants
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    // Refer to https://openweathermap.org/current for API doc
    // https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
    @GET(value = "data/2.5/weather")
    fun getWeather(
        @Query("lat")
        lat: String,
        @Query("lon")
        lon: String,
        @Query("appid")
        appId: String = Constants.API_KEY,
        // TODO VM-2: Add feature allow users to switch between imperial and metric
        @Query("units")
        units: String = "imperial"
    ): Observable<Weather>
}
