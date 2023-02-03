package com.mikali.weathermemoir.model

import com.squareup.moshi.Json

data class Rain(
    @Json(name = "1h")
    val oneHour: Double? = null,
    @Json(name = "3h")
    val threeHours: Double? = null
)
