package com.imuaythai.mtjudges.login.service

import androidx.lifecycle.LiveData
import com.imuaythai.mtjudges.provider.webservice.dto.UserData

interface UserService {

    fun setUserData(userData: UserData)

    fun getUserData() : UserData

    fun provideUserDataObservable() : LiveData<UserData>

}