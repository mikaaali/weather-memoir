package com.mikali.weathermemoir.model

import com.squareup.moshi.Json

data class Clouds(
    @Json(name = "all")
    private val _all: Int? = null
) {
    val all = if (_all != null) {
        "Cloudiness: $_all%"
    } else {
        "N/A"
    }
}
