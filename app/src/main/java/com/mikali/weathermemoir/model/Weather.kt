package com.mikali.weathermemoir.model

data class Weather(
    val coord: Coord? = null,
    val weather: List<WeatherCondition>? = emptyList(),
    val main: Main? = null,
    val visibility: Int? = null,
    val wind: Wind? = null,
    val clouds: Clouds? = null,
    val rain: Rain? = null,
    val snow: Snow? = null,
    val dt: Int? = null,
    val sys: Sys? = null,
    val timezone: String? = null,
    val id: Int? = null,
    val name: String? = null
)
