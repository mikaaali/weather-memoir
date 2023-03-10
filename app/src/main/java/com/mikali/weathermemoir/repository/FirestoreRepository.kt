package com.mikali.weathermemoir.repository

import com.mikali.weathermemoir.model.UserInfo
import io.reactivex.Single

interface FirestoreRepository {

    fun getAllUserInfo(): Single<List<UserInfo>>

    fun saveUserInfo(userInfo: UserInfo)
}
