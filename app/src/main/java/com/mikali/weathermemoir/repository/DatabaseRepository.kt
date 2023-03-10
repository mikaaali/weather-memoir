package com.mikali.weathermemoir.repository

import com.mikali.weathermemoir.db.ThoughtEntries
import com.mikali.weathermemoir.db.User
import io.reactivex.Observable
import io.reactivex.Single
import java.time.ZonedDateTime

interface DatabaseRepository {

    fun getThoughtEntries(searchQuery: String?): Observable<List<ThoughtEntries>>

    fun saveThoughtEntries(
        uid: String,
        question: String,
        thought: String,
        creationTime: ZonedDateTime? = null,
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
