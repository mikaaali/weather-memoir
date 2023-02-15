package com.mikali.weathermemoir.database

import app.cash.sqldelight.ColumnAdapter
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * adapter to teach the project how to convert between SQL and kotlin data type.
 *
 * Not all SQL column type has a built in corresponding mapping to kotlin field type
 *
 * ColumnAdapter have two types, the first is the kotlin type, second is the raw SQL type
 */
val zonedDateTimeAdapter = object : ColumnAdapter<ZonedDateTime, Long> {
    override fun decode(databaseValue: Long): ZonedDateTime =
        ZonedDateTime.ofInstant(Instant.ofEpochSecond(databaseValue), ZoneId.of("UTC"))

    override fun encode(value: ZonedDateTime): Long =
        value.toEpochSecond()
}
