package com.mikali.weathermemoir.model

import com.squareup.moshi.Json

data class Weather(
    val coord: Coord? = null,
    val weather: List<WeatherCondition>? = emptyList(),
    val main: Main? = null,
    @Json(name = "visibility")
    private val _visibility: Int? = null,
    val wind: Wind? = null,
    val clouds: Clouds? = null,
    val rain: Rain? = null,
    val snow: Snow? = null,
    val dt: Int? = null,
    val sys: Sys? = null,
    val timezone: String? = null,
    val id: Int? = null,
    val name: String = "Unknown"
) {
    val visibility = if (_visibility != null)"Visibility: ${_visibility.div(1000)}km" else "Visibility: N/A km"
}
