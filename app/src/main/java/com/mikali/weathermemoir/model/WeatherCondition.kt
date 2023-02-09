package com.mikali.weathermemoir.model

data class WeatherCondition(
    val id: Int? = null,
    val main: String = "N/A",
    val description: String = "N/A",
    val icon: String? = null
)
