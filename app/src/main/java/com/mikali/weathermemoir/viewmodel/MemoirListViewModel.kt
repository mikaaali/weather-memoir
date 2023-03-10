package com.mikali.weathermemoir.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mikali.weathermemoir.model.UserInfo
import com.mikali.weathermemoir.repository.DatabaseRepository
import com.mikali.weathermemoir.util.Constants
import com.mikali.weathermemoir.util.Constants.dateTimeFormatter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class MemoirListViewModel(
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val behaviorSubject = BehaviorSubject.create<MutableState>()
    val listObservable = behaviorSubject

    private val currentMutableState
        get() = behaviorSubject.value
            ?: MutableState(listOfAnswers = emptyList(), searchQuery = "")

    data class MutableState(
        val listOfAnswers: List<UserInfo.Memoir>,
        val searchQuery: String
    )

    fun onSearchValueChange(query: String) {
        behaviorSubject.onNext(currentMutableState.copy(searchQuery = query))
    }

    fun onClearSearchQuery() {
        compositeDisposable.add(
            databaseRepository.getThoughtEntries(searchQuery = "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .firstOrError()
                .map {
                    val list = mutableListOf<UserInfo.Memoir>()
                    it.forEach { thoughtEntries ->
                        list.add(
                            UserInfo.Memoir(
                                question = thoughtEntries.question, // it:ThoughtEntries
                                thought = thoughtEntries.thought ?: "",
                                creationTime = dateTimeFormatter.format(thoughtEntries.creationTime),
                                mainWeatherConditionIcon = thoughtEntries.mainWeatherConditionIcon,
                                mainWeatherConditionDescription = thoughtEntries.mainWeatherConditionDescription
                            )
                        )
                    }
                    list
                }
                .subscribe(
                    {
                        behaviorSubject.onNext(currentMutableState.copy(searchQuery = "", listOfAnswers = it))
                    },
                    {
                        Log.e(
                            Constants.MEMOIR_LIST_TAG,
                            "Error subscribing to get all thought entries from database",
                            it
                        )
                    }
                )
        )
    }

    fun getThoughtEntries(searchQuery: String? = null) {
        Log.d("haha search query", searchQuery.toString())
        compositeDisposable.add(
            databaseRepository.getThoughtEntries(searchQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .firstOrError()
                .map {
                    val list = mutableListOf<UserInfo.Memoir>()
                    it.forEach { thoughtEntries ->
                        list.add(
                            UserInfo.Memoir(
                                question = thoughtEntries.question, // it:ThoughtEntries
                                thought = thoughtEntries.thought ?: "",
                                creationTime = dateTimeFormatter.format(thoughtEntries.creationTime),
                                mainWeatherConditionIcon = thoughtEntries.mainWeatherConditionIcon,
                                mainWeatherConditionDescription = thoughtEntries.mainWeatherConditionDescription
                            )
                        )
                    }
                    list
                }
                .subscribe(
                    {
                        Log.d("haha did I enter the success block", it.toString())
                        behaviorSubject.onNext(currentMutableState.copy(listOfAnswers = it))
                    },
                    {
                        Log.e(
                            Constants.MEMOIR_LIST_TAG,
                            "Error subscribing to getThoughtEntries() from database",
                            it
                        )
                    }
                )
        )
    }
}
