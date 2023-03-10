package com.mikali.weathermemoir.database

import com.mikali.weathermemoir.db.ThoughtEntries
import com.mikali.weathermemoir.db.User
import io.reactivex.Observable
import io.reactivex.Single
import java.time.ZonedDateTime

interface DatabaseDao {

    fun getAllThoughtEntries(): Observable<List<ThoughtEntries>>

    fun getThoughtEntries(searchQuery: String): Observable<List<ThoughtEntries>>

    fun insertOrUpdateThoughtEntries(
        uid: String,
        question: String,
        thought: String,
        creationTime: ZonedDateTime?,
        mainWeatherConditionIcon: String,
        mainWeatherConditionDescription: String
    )

    fun getUser(): Single<User>

    fun saveUser(
        uid: String,
        firstName: String,
        lastName: String
    )
}
