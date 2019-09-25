package com.imuaythai.mtjudges.time.judge

import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.common.live.BooleanLiveData
import com.imuaythai.mtjudges.provider.dto.FightState
import com.imuaythai.mtjudges.service.FightRepository
import javax.inject.Inject

class TimeJudgeViewModel @Inject constructor(
    private val fightRepository: FightRepository
) : BaseViewModel(){

    val actionButtonText = MutableLiveData<String>()

    val isActionButtonEnabled = BooleanLiveData(false)

    val isStopButtonEnabled = BooleanLiveData(false)

    val isSignalButtonEnabled  = BooleanLiveData(false)

    private var fightStatusDisposable = fightRepository.provideFightStatusObservable().subscribe { fightState ->

        when(fightState.state){
            FightState.WAITING -> {
                isActionButtonEnabled.value = false
                isStopButtonEnabled.value = false
                isSignalButtonEnabled.value = false
                actionButtonText.value = "START"
            }
            FightState.STARTED -> {
                isActionButtonEnabled.value = true
                isStopButtonEnabled.value = true
                isSignalButtonEnabled.value = false
                actionButtonText.value = "PAUSE"
            }
            FightState.PAUSED -> {
                isActionButtonEnabled.value = true
                isStopButtonEnabled.value = false
                isSignalButtonEnabled.value = false
                actionButtonText.value = "START"
            }
            FightState.STOPPED -> {
                isActionButtonEnabled.value = false
                isStopButtonEnabled.value = false
                isSignalButtonEnabled.value = false
                actionButtonText.value = "PAUSE"
            }
            FightState.BREAK -> {
                isActionButtonEnabled.value = false
                isStopButtonEnabled.value = false
                isSignalButtonEnabled.value = false
                actionButtonText.value = "PAUSE"
            }
            FightState.ENDED -> {
                isActionButtonEnabled.value = false
                isStopButtonEnabled.value = false
                isSignalButtonEnabled.value = false
                actionButtonText.value = "PAUSE"
            }
            else -> {
                isActionButtonEnabled.value = false
                isStopButtonEnabled.value = false
                isSignalButtonEnabled.value = false
                actionButtonText.value = "START"
            }
        }
    }

    fun onClickActionButton(){

    }

    fun onClickStopButton(){

    }

    fun onClickSignalButton(){

    }

    override fun onCleared() {
        super.onCleared()
        fightStatusDisposable.dispose()
    }

}