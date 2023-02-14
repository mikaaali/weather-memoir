package com.mikali.weathermemoir.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class SignupViewModel() : ViewModel() {

    data class MutableState(
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: String
    )

    private val behaviorSubject: BehaviorSubject<MutableState> = BehaviorSubject.create()
    val signupObservable: Observable<MutableState> = behaviorSubject

    private val currentMutableState get() = behaviorSubject.value
        ?: MutableState("", "", "", "")

    fun onFirstNameValueChange(firstName: String) {
        behaviorSubject.onNext(currentMutableState.copy(firstName = firstName))
    }

    fun onLastNameValueChange(lastName: String) {
        behaviorSubject.onNext(currentMutableState.copy(lastName = lastName))
    }

    fun onEmailValueChange(email: String) {
        behaviorSubject.onNext(currentMutableState.copy(email = email))
    }

    fun onPasswordValueChange(password: String) {
        behaviorSubject.onNext(currentMutableState.copy(password = password))
    }
}
