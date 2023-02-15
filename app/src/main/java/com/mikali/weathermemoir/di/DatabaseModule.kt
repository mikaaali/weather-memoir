package com.mikali.weathermemoir.di

import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.mikali.weathermemoir.database.zonedDateTimeAdapter
import com.mikali.weathermemoir.db.SQLDelightDatabase
import com.mikali.weathermemoir.db.ThoughtEntries
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    // define database instance
    single<SQLDelightDatabase> {
        SQLDelightDatabase(
            driver = AndroidSqliteDriver(
                schema = SQLDelightDatabase.Schema,
                context = androidContext(),
                name = "weather-memoir.db", // name of the database file on disk
                callback = object :
                    AndroidSqliteDriver.Callback(schema = SQLDelightDatabase.Schema) {
                    override fun onConfigure(db: SupportSQLiteDatabase) {
                        super.onConfigure(db)
                        db.setForeignKeyConstraintsEnabled(true) // allow us to use the foreign key in sql
                    }
                }
            ),
            ThoughtEntriesAdapter = ThoughtEntries.Adapter(
                creationTimeAdapter = zonedDateTimeAdapter
            )
        )
    }
}
