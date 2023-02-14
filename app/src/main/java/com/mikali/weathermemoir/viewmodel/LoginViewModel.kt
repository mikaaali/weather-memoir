package com.mikali.weathermemoir.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class LoginViewModel() : ViewModel() {

    data class MutableState(
        val email: String,
        val password: String
    )

    private val behaviorSubject: BehaviorSubject<MutableState> = BehaviorSubject.create() // used internally inside the class
    val loginObservable: Observable<MutableState> = behaviorSubject // used outside of the class

    private val currentMutableState: MutableState
        get() = behaviorSubject.value
            ?: MutableState("", "")

    fun onEmailFieldChange(email: String) {
        behaviorSubject.onNext(currentMutableState.copy(email = email))
    }

    fun onPasswordFieldChange(password: String) {
        behaviorSubject.onNext(currentMutableState.copy(password = password))
    }
}
