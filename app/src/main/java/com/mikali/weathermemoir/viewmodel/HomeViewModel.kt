package com.mikali.weathermemoir.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mikali.weathermemoir.model.UserInfo
import com.mikali.weathermemoir.model.weather.Weather
import com.mikali.weathermemoir.repository.WeatherRepository
import com.mikali.weathermemoir.util.Constants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class HomeViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    // Mutable, such that new elements can be added onto a subject at runtime
    private val behaviorSubject: BehaviorSubject<MutableState> = BehaviorSubject.create()

    // Not mutable, we are subscribing to this as state in the view
    val homeObservable: Observable<MutableState> = behaviorSubject

    private val loadingSubject: PublishSubject<Boolean> = PublishSubject.create()
    val loadingObservable: Observable<Boolean> = loadingSubject

    private val currentMutableState
        get() = behaviorSubject.value ?: MutableState(
            weather = Weather(),
            memoir = UserInfo.Memoir(
                question = "What is your earliest memory?",
                thought = "The most vivid memory I had when I was a kid traces back to the year I turned 3. I was in the park playing with puppies...",
                creationTime = "Feb 8, 2023",
                mainWeatherConditionIcon = "04n",
                mainWeatherConditionDescription = ""
            )
        )

    data class MutableState(
        val weather: Weather,
        val memoir: UserInfo.Memoir
    )

    fun getWeather(lat: String, lon: String) {
        loadingSubject.onNext(true)

        compositeDisposable.add(
            weatherRepository.getWeather(lat = lat, lon = lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        behaviorSubject.onNext(currentMutableState.copy(weather = it))
                        loadingSubject.onNext(false)
                    },
                    {
                        loadingSubject.onNext(false)
                        Log.e(Constants.HOME_TAG, "Error subscribing to getWeather()", it)
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
