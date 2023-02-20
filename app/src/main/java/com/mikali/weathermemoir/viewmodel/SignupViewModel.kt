package com.mikali.weathermemoir.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mikali.weathermemoir.model.UserInfo
import com.mikali.weathermemoir.navigation.NavigationScreens
import com.mikali.weathermemoir.util.Constants
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class SignupViewModel(
    private val firebaseAuth: FirebaseAuth
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
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { authResult ->
                    if (authResult.isSuccessful) {
                        navController.navigate(route = NavigationScreens.MAIN.name)
                        saveNewUserToFirestore()
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

    private fun saveNewUserToFirestore() {
        val user = UserInfo(
            uid = firebaseAuth.currentUser?.uid.toString(),
            firstName = currentMutableState.firstName,
            lastName = currentMutableState.lastName,
            email = currentMutableState.email,
            memoirList = emptyList()
        ).toMutableMap()

        FirebaseFirestore.getInstance().collection("user_info").add(user)
    }
}
