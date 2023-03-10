package com.mikali.weathermemoir.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mikali.weathermemoir.model.UserInfo
import com.mikali.weathermemoir.repository.DatabaseRepository
import com.mikali.weathermemoir.repository.FirestoreRepository
import com.mikali.weathermemoir.util.Constants
import com.mikali.weathermemoir.util.Constants.dateTimeFormatter
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val databaseRepository: DatabaseRepository,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun onLogoutClick() {
        compositeDisposable.add(
            Single.zip(
                databaseRepository.getUser(),
                databaseRepository.getThoughtEntries(searchQuery = null).firstOrError()
            ) { user, thoughtEntryList ->
                val memoirList = mutableListOf<UserInfo.Memoir>()
                thoughtEntryList.forEach { thoughtEntries ->
                    memoirList.add(
                        UserInfo.Memoir(
                            question = thoughtEntries.question,
                            thought = thoughtEntries.thought ?: "",
                            creationTime = dateTimeFormatter.format(thoughtEntries.creationTime),
                            mainWeatherConditionIcon = thoughtEntries.mainWeatherConditionIcon,
                            mainWeatherConditionDescription = thoughtEntries.mainWeatherConditionDescription
                        )
                    )
                }
                Log.d("haha memoirList: ", memoirList.toString())

                UserInfo(
                    firestoreUid = "",
                    authUid = user.uid,
                    firstName = user.firstname,
                    lastName = user.lastname ?: "",
                    memoirList = memoirList
                )
            }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                    {
                        firestoreRepository.saveUserInfo(userInfo = it)
                    },
                    {
                        Log.e(
                            Constants.MAIN_TAG,
                            "Error getting data from local database",
                            it
                        )
                    }
                )
        )
    }
}
