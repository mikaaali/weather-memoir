package com.mikali.weathermemoir.model

import com.squareup.moshi.Json

data class Main(
    @Json(name = "temp")
    private val _temp: Double? = null,
    @Json(name = "feels_like")
    private val _feelsLike: Double? = null,
    @Json(name = "pressure")
    private val _pressure: Int? = null,
    @Json(name = "humidity")
    private val _humidity: Int? = null,
    @Json(name = "temp_min")
    private val _tempMin: Double? = null,
    @Json(name = "temp_max")
    private val _tempMax: Double? = null
) {
    val temp = if (_temp != null)"${_temp.toInt()} ºF" else "N/A"
    val feelsLike = if (_feelsLike != null) "Feels like ${_feelsLike.toInt()} ºF" else "Feels like N/A"
    val pressure = if (_pressure != null) "Pressure: ${_pressure}hPa" else "Pressure: N/A"
    val humidity = if (_humidity != null)"Humidity: $_humidity%" else "Humidity: N/A%"
    val maxMinTemp = if (_tempMax != null && _tempMin != null) { "H:${_tempMax}º   L:${_tempMin}º" } else { "H:N/Aº   L:N/Aº" }
}
