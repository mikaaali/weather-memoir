package com.mikali.weathermemoir.di

import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.mikali.weathermemoir.database.DatabaseDao
import com.mikali.weathermemoir.database.DatabaseDaoImpl
import com.mikali.weathermemoir.database.zonedDateTimeAdapter
import com.mikali.weathermemoir.db.SQLDelightDatabase
import com.mikali.weathermemoir.db.ThoughtEntries
import com.mikali.weathermemoir.repository.DatabaseRepository
import com.mikali.weathermemoir.repository.DatabaseRepositoryImpl
import com.mikali.weathermemoir.repository.FirestoreRepository
import com.mikali.weathermemoir.repository.FirestoreRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    // define local sqlDelight database instance
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

    // define local databaseDao instance
    single<DatabaseDao> {
        DatabaseDaoImpl(database = get())
    }

    // define local databaseRepository instance
    single<DatabaseRepository> {
        DatabaseRepositoryImpl(databaseDao = get())
    }

    // define remote firestore database
    single<FirestoreRepository> {
        FirestoreRepositoryImpl()
    }
}
