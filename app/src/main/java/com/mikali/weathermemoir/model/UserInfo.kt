package com.mikali.weathermemoir.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.PropertyName

data class UserInfo(
    @Exclude
    @get:PropertyName("firestore_uid")
    @set:PropertyName("firestore_uid")
    var firestoreUid: String? = null,
    @get:PropertyName("auth_uid")
    @set:PropertyName("auth_uid")
    var authUid: String? = null,
    @get:PropertyName("first_name")
    @set:PropertyName("first_name")
    var firstName: String? = null,
    @get:PropertyName("last_name")
    @set:PropertyName("last_name")
    var lastName: String? = null,
    @get:PropertyName("memoir_list")
    @set:PropertyName("memoir_list")
    var memoirList: List<Memoir>? = emptyList()
) {
    data class Memoir(
        val question: String = "",
        val thought: String = "",
        val creationTime: String = "",
        val mainWeatherConditionIcon: String = "",
        val mainWeatherConditionDescription: String = ""
    )
}
