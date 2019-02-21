package com.imuaythai.mtjudges.login.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.provider.webservice.dto.UserData
import javax.inject.Inject

class UserServiceImpl @Inject constructor() : UserService {

    private val userDataObservable : MutableLiveData<UserData> = MutableLiveData()

    override fun setUserData(userData: UserData){
        userDataObservable.value = userData
    }

    override fun getUserData(): UserData = userDataObservable.value ?: UserData()

    override fun provideUserDataObservable(): LiveData<UserData> = userDataObservable
}