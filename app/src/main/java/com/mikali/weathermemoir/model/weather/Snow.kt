package com.mikali.weathermemoir.model.weather

import com.squareup.moshi.Json

data class Snow(
    @Json(name = "1h")
    private val _oneHour: Double? = null,
    @Json(name = "3h")
    private val _threeHours: Double? = null
) {
    val oneHour = if (_oneHour != null)"Snow for last hour: ${_oneHour}mm" else "Snow for last hour: N/A mm"
    val threeHours = if (_threeHours != null)"Snow for last three: ${_threeHours}mm" else "Snow for last three: N/A mm"
}
