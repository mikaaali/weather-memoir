package com.mikali.weathermemoir

import android.app.Application
import com.google.firebase.FirebaseApp

/* The application class is instantiated before any other classes when an application starts.
This is a good place to setup koin dependency injection, start
* */
class WeatherMemoirApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }
}
