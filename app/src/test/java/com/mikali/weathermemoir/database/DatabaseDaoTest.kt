package com.mikali.weathermemoir.database

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver.Companion.IN_MEMORY
import com.mikali.weathermemoir.db.SQLDelightDatabase
import com.mikali.weathermemoir.db.ThoughtEntries
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.ZonedDateTime

class DatabaseDaoTest {

    // need to create the database schema when the driver is created
    // android driver does it under the hood
    private val testDriver = JdbcSqliteDriver(IN_MEMORY).also(SQLDelightDatabase.Schema::create)

    @Test
    fun `test add new thought entries and retrieve it`() {
        // since this is a unit test, we don't have access to Android context
        // therefore we have to use a different driver, hence Java database driver
        val testDatabase = SQLDelightDatabase(
            driver = testDriver,
            ThoughtEntriesAdapter = ThoughtEntries.Adapter(
                creationTimeAdapter = zonedDateTimeAdapter
            )
        )
        val dao = DatabaseDaoImpl(database = testDatabase)

        dao.insertOrUpdateThoughtEntries(
            uid = "fbaobid70jbs",
            question = "How's your day?",
            thought = "good",
            mainWeatherConditionDescription = "Sunny",
            mainWeatherConditionIcon = "04d",
            creationTime = ZonedDateTime.now()
        )

        val thoughtEntriesList = dao.getAllThoughtEntries().blockingFirst()
        assertEquals("good", thoughtEntriesList[0].thought)
    }
}
