package com.imuaythai.mtjudges.splash

import android.os.Handler
import android.os.Looper
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.navigation.NavigateToLoginActivityAction
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel() {

    init {
        Handler(Looper.getMainLooper())
            .postDelayed(
                {  navigate(NavigateToLoginActivityAction()) },
                1200
            )
    }

}