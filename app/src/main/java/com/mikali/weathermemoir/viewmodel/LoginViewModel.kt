package com.mikali.weathermemoir.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.mikali.weathermemoir.navigation.NavigationScreens
import com.mikali.weathermemoir.util.Constants
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(
    private val firebaseAuth: FirebaseAuth
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
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { authResult ->
                    if (authResult.isSuccessful) {
                        // take the user main screen
                        navController.navigate(route = NavigationScreens.MAIN.name)
                        Log.d("haha", "Login successful")
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
