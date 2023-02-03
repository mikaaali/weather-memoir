package com.mikali.weathermemoir.model

import com.squareup.moshi.Json

data class Main(
    val temp: Double? = null,
    @Json(name = "feels_like")
    val feelsLike: Double? = null,
    val pressure: Int? = null,
    val humidity: Int? = null,
    @Json(name = "temp_min")
    val tempMin: Double? = null,
    @Json(name = "temp_max")
    val tempMax: Double? = null
)
