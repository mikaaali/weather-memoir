package com.mikali.weathermemoir.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mikali.weathermemoir.navigation.NavigationScreens
import com.mikali.weathermemoir.repository.DatabaseRepository
import com.mikali.weathermemoir.util.Constants
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class SignupViewModel(
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    data class MutableState(
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: String
    )

    private val behaviorSubject: BehaviorSubject<MutableState> = BehaviorSubject.create()
    val signupObservable: Observable<MutableState> = behaviorSubject

    private val currentMutableState
        get() = behaviorSubject.value
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

    fun signupWithEmailAndPassword(
        email: String,
        password: String,
        navController: NavController
    ) {
        try {
            Firebase.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { authResult ->
                    if (authResult.isSuccessful) {
                        navController.navigate(route = NavigationScreens.MAIN.name)
                        saveNewUserToLocalDatabase()
                        Log.d("haha", "Signup successful")
                    } else {
                        // show dialog with the error message
                        Log.d("haha", "Did not signup successful")
                    }
                }
        } catch (e: Exception) {
            Log.e(Constants.SIGNUP_TAG, "signupWithEmailAndPassword : ${e.message}")
        }
    }

    private fun saveNewUserToLocalDatabase() {
        Firebase.auth.currentUser?.uid.let {
            databaseRepository.saveUser(
                uid = it ?: "",
                firstName = currentMutableState.firstName,
                lastName = currentMutableState.lastName
            )
        }
    }
}
