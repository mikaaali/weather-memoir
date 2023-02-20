package com.mikali.weathermemoir.model

data class UserInfo(
    val uid: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val memoirList: List<Memoir>
) {

    data class Memoir(
        val question: String,
        val thought: String,
        val creationTime: String,
        val mainWeatherConditionDescription: String
    )

    fun toMutableMap(): MutableMap<String, Any> = mutableMapOf(
        "uid" to this.uid,
        "first_name" to this.firstName,
        "last_name" to this.lastName,
        "email" to this.email,
        "memoir_list" to this.memoirList
    )
}
