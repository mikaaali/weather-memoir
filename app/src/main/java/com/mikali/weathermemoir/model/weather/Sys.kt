package com.mikali.weathermemoir.model.weather

data class Sys(
    val type: Int? = null,
    val id: Int? = null,
    val country: String = "Unknown",
    val sunrise: Int? = null,
    val sunset: Int? = null
)
