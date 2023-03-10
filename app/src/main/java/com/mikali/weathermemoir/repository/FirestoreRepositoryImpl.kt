package com.mikali.weathermemoir.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.mikali.weathermemoir.model.UserInfo
import com.mikali.weathermemoir.util.Constants
import io.reactivex.Single

class FirestoreRepositoryImpl() : FirestoreRepository {

    private val dbCollection = FirebaseFirestore.getInstance().collection("user_info")

    override fun getAllUserInfo(): Single<List<UserInfo>> {
        return Single.create { emitter ->

            dbCollection.get().addOnSuccessListener {
                val userInfoList = it.toObjects(UserInfo::class.java)
                emitter.onSuccess(userInfoList)
            }
        }
    }

    override fun saveUserInfo(userInfo: UserInfo) {
        dbCollection.add(userInfo)
            .addOnSuccessListener { documentReference ->
                dbCollection.document(documentReference.id)
                    .update(hashMapOf("firestore_uid" to documentReference.id) as Map<String, Any>)
                    .addOnCompleteListener {
                    }.addOnFailureListener { exception ->
                        Log.e(
                            Constants.MAIN_TAG,
                            "Error updating firestore_id: ${exception.message}"
                        )
                    }
            }
    }
}
