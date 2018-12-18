package com.imuaythai.mtjudges.splash

import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.navigation.NavigateToMainActivityAction
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel() {

    init {
        navigate(NavigateToMainActivityAction())
    }

}