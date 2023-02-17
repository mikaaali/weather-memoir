package com.mikali.weathermemoir.model.weather

import com.squareup.moshi.Json

data class Rain(
    @Json(name = "1h")
    private val _oneHour: Double? = null,
    @Json(name = "3h")
    private val _threeHours: Double? = null
) {
    val oneHour = if (_oneHour != null)"Rain for last hour: ${_oneHour}mm" else "Rain for last hour: N/A mm"
    val threeHours = if (_threeHours != null)"Rain for last three: ${_threeHours}mm" else "Rain for last three: N/A mm"
}
