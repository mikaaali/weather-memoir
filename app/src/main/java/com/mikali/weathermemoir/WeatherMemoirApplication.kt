package com.mikali.weathermemoir

import android.app.Application
import com.google.firebase.FirebaseApp
import com.mikali.weathermemoir.di.appModule
import com.mikali.weathermemoir.di.databaseModule
import com.mikali.weathermemoir.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/* The application class is instantiated before any other classes when an application starts.
This is a good place to setup koin dependency injection, this way all the created objects will
be available to all classes.
* */
class WeatherMemoirApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // initialize firebase analytics
        FirebaseApp.initializeApp(this)

        startKoin {
            androidLogger()
            androidContext(this@WeatherMemoirApplication)
            modules(listOf(networkModule, appModule, databaseModule))
        }
    }
}
