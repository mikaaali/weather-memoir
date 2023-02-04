package com.mikali.weathermemoir.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mikali.weathermemoir.model.Weather
import com.mikali.weathermemoir.repository.WeatherRepository
import com.mikali.weathermemoir.util.Constants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class MainViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    // Mutable, such that new elements can be added onto a subject at runtime
    private val weatherSubject: BehaviorSubject<Weather> = BehaviorSubject.create()

    // Not mutable, we are subscribing to this as state in the view
    val weatherObservable: Observable<Weather> = weatherSubject

    private val loadingSubject: PublishSubject<Boolean> = PublishSubject.create()
    val loadingObservable: Observable<Boolean> = loadingSubject

    fun getWeather(lat: String, lon: String) {
        loadingSubject.onNext(true)

        compositeDisposable.add(
            weatherRepository.getWeather(lat = lat, lon = lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        weatherSubject.onNext(it)
                        loadingSubject.onNext(false)
                    },
                    {
                        loadingSubject.onNext(false)
                        Log.e(Constants.MAIN_TAG, "Error subscribing to getWeather()", it)
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
