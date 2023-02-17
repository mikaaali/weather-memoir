package com.mikali.weathermemoir.model.weather

import com.squareup.moshi.Json

data class Wind(
    @Json(name = "deg")
    private val _deg: Int? = null,
    @Json(name = "gust")
    private val _gust: Double? = null,
    @Json(name = "speed")
    private val _speed: Double? = null
) {
    private val deg = if (_deg != null) "$_deg" else "N/A"
    private val gust = if (_gust != null) "$_gust" else "N/A"
    private val speed = if (_speed != null) "$_speed" else "N/A"

    val value = "Wind: ${deg}º | Speed: ${speed}mi/hr | Gust: ${gust}mi/hr"
}
