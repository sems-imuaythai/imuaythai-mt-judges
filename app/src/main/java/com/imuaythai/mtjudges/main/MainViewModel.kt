package com.imuaythai.mtjudges.main

import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.navigation.NavigateToLoginFragmentAction
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {

    init {
        navigate(NavigateToLoginFragmentAction(artistid = 1001))
    }

}