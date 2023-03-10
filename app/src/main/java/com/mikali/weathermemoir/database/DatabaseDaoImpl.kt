package com.mikali.weathermemoir.database

import com.mikali.weathermemoir.db.SQLDelightDatabase
import com.mikali.weathermemoir.db.ThoughtEntries
import com.mikali.weathermemoir.db.User
import com.squareup.sqldelight.runtime.rx.asObservable
import com.squareup.sqldelight.runtime.rx.mapToList
import com.squareup.sqldelight.runtime.rx.mapToOne
import io.reactivex.Observable
import io.reactivex.Single
import java.time.ZoneId
import java.time.ZonedDateTime

class DatabaseDaoImpl(private val database: SQLDelightDatabase) : DatabaseDao {

    override fun getAllThoughtEntries(): Observable<List<ThoughtEntries>> {
        return database.thoughtEntriesQueries.all() // Query<ThoughtEntries>
            .asObservable() // transform Query into Observable type  Observable<Query<ThoughtEntries>>
            .mapToList()
    }

    override fun getThoughtEntries(searchQuery: String): Observable<List<ThoughtEntries>> {
        return database.thoughtEntriesQueries.searchResult(value_ = searchQuery) // Query<ThoughtEntries>
            .asObservable() // transform Query into Observable type  Observable<Query<ThoughtEntries>>
            .mapToList()
    }

    override fun insertOrUpdateThoughtEntries(
        uid: String,
        question: String,
        thought: String,
        creationTime: ZonedDateTime?,
        mainWeatherConditionIcon: String,
        mainWeatherConditionDescription: String
    ) {
        database.thoughtEntriesQueries.insertOrUpdateThoughtEntries(
            uid = uid,
            question = question,
            thought = thought,
            creationTime = creationTime ?: ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC")),
            mainWeatherConditionIcon = mainWeatherConditionIcon,
            mainWeatherConditionDescription = mainWeatherConditionDescription
        )
    }

    override fun getUser(): Single<User> {
        return database.userQueries.getUser()
            .asObservable() // Observable<Query<User>>
            .mapToOne() // Observable<User>
            .firstOrError()
    }

    override fun saveUser(
        uid: String,
        firstName: String,
        lastName: String
    ) {
        database.userQueries.insertUser(
            uid = uid,
            firstname = firstName,
            lastname = lastName
        )
    }
}
