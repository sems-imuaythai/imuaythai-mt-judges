package com.imuaythai.mtjudges.common.view

import androidx.lifecycle.LiveData
import com.imuaythai.mtjudges.application.navigation.RestartApplicationAction
import com.imuaythai.mtjudges.common.BaseViewModel

class ConfigurationChangeObserver(private val baseViewModel: BaseViewModel){

    private var configurationChangeId: Int = 0

    fun observe(configurationChangeObservable: LiveData<Int>){
        configurationChangeObservable.observeForever { value ->
            if (configurationChangeId != 0 && configurationChangeId != value) {
                baseViewModel.navigate(RestartApplicationAction())
            } else {
                configurationChangeId = value
            }
        }
    }

}