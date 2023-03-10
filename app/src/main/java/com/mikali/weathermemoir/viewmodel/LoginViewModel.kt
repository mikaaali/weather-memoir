package com.mikali.weathermemoir.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mikali.weathermemoir.navigation.NavigationScreens
import com.mikali.weathermemoir.repository.DatabaseRepository
import com.mikali.weathermemoir.repository.FirestoreRepository
import com.mikali.weathermemoir.util.Constants
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

class LoginViewModel(
    private val databaseRepository: DatabaseRepository,
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {

    data class MutableState(
        val email: String,
        val password: String,
        val errorMessage: String
    )

    private val behaviorSubject: BehaviorSubject<MutableState> = BehaviorSubject.create() // used internally inside the class
    val loginObservable: Observable<MutableState> = behaviorSubject // used outside of the class

    private val currentMutableState: MutableState
        get() = behaviorSubject.value
            ?: MutableState("", "", "")

    fun onEmailFieldChange(email: String) {
        behaviorSubject.onNext(currentMutableState.copy(email = email))
    }

    fun onPasswordFieldChange(password: String) {
        behaviorSubject.onNext(currentMutableState.copy(password = password))
    }

    // the reason we wrap it inside a viewModelScope because we want to Launches a new coroutine without blocking the current main thread
    fun loginWithEmailAndPassword(email: String, password: String, navController: NavController): Job = viewModelScope.launch {
        try {
            Firebase.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { authResult ->
                    if (authResult.isSuccessful) {
                        // get user info from firebase firestore database
                        firestoreRepository.getAllUserInfo()
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io())
                            .subscribe(
                                {
                                    val first = it[0]
                                    // save user info to local sqlDelight database
                                    first.authUid?.let { authUid ->
                                        databaseRepository.saveUser(
                                            uid = authUid,
                                            firstName = first.firstName ?: "",
                                            lastName = first.lastName ?: ""
                                        )
                                    }
                                    first.memoirList?.forEach { memoir ->
                                        first.authUid?.let { authUid ->
                                            databaseRepository.saveThoughtEntries(
                                                uid = authUid,
                                                question = memoir.question,
                                                thought = memoir.thought,
                                                creationTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(memoir.creationTime.toLong()), ZoneId.of("UTC")),
                                                mainWeatherConditionIcon = memoir.mainWeatherConditionIcon,
                                                mainWeatherConditionDescription = memoir.mainWeatherConditionDescription
                                            )
                                        }
                                    }
                                },
                                {
                                    Log.e(Constants.LOGIN_TAG, "Error getting user info from remote firestore database", it)
                                }
                            )

                        // take the user main screen
                        navController.navigate(route = NavigationScreens.MAIN.name)
                    } else {
                        // show dialog with the error message
                        behaviorSubject.onNext(currentMutableState.copy(errorMessage = "Not able to Login.\nTry again"))
                    }
                }
        } catch (e: Exception) {
            behaviorSubject.onNext(currentMutableState.copy(errorMessage = "${e.message}"))
            Log.e(Constants.LOGIN_TAG, "loginWithEmailAndPassword: ${e.message}")
        }
    }

    fun onErrorConfirmationClick() {
        behaviorSubject.onNext(currentMutableState.copy(errorMessage = ""))
    }
}
