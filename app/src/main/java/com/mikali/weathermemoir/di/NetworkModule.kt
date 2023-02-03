package com.mikali.weathermemoir.di

import com.mikali.weathermemoir.BuildConfig
import com.mikali.weathermemoir.network.WeatherAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// koin dependency injection module for network stuff
val networkModule = module {

    // Weather API Base URL
    single(named("BASE_URL")) {
        "https://api.openweathermap.org/"
    }

    // logging interceptor instance
    single {
        // this allow us to log all request and response in the Logcat, it's only applied to debug variants
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    // OkHttpClient instance
    single {
        val client = OkHttpClient().newBuilder()
        if (BuildConfig.DEBUG) {
            client.addInterceptor(get<HttpLoggingInterceptor>())
        }
        client.build()
    }

    // moshi instance
    single {
        // Moshi allow us to use @Json annotation
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    // retrofit instance
    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named("BASE_URL")))
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
    }

    // WeatherAPI instance
    single {
        get<Retrofit>().create(WeatherAPI::class.java)
    }
}
