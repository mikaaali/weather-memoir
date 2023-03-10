package com.mikali.weathermemoir.repository

import com.mikali.weathermemoir.database.DatabaseDao
import com.mikali.weathermemoir.db.ThoughtEntries
import com.mikali.weathermemoir.db.User
import io.reactivex.Observable
import io.reactivex.Single
import java.time.ZonedDateTime

class DatabaseRepositoryImpl(
    private val databaseDao: DatabaseDao
) : DatabaseRepository {

    override fun getThoughtEntries(searchQuery: String?): Observable<List<ThoughtEntries>> =
        if (searchQuery.isNullOrBlank()) {
            databaseDao.getAllThoughtEntries()
        } else {
            databaseDao.getThoughtEntries(searchQuery)
        }

    override fun saveThoughtEntries(
        uid: String,
        question: String,
        thought: String,
        creationTime: ZonedDateTime?,
        mainWeatherConditionIcon: String,
        mainWeatherConditionDescription: String
    ) {
        return databaseDao.insertOrUpdateThoughtEntries(
            uid = uid,
            question = question,
            thought = thought,
            creationTime = creationTime,
            mainWeatherConditionIcon = mainWeatherConditionIcon,
            mainWeatherConditionDescription = mainWeatherConditionDescription.lowercase()
        )
    }

    override fun getUser(): Single<User> {
        return databaseDao.getUser()
    }

    override fun saveUser(
        uid: String,
        firstName: String,
        lastName: String
    ) {
        databaseDao.saveUser(
            uid = uid,
            firstName = firstName,
            lastName = lastName
        )
    }
}
