package com.imuaythai.mtjudges.time.judge

import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.common.live.BooleanLiveData
import com.imuaythai.mtjudges.provider.dto.FightState
import com.imuaythai.mtjudges.service.FightRepository
import com.imuaythai.mtjudges.time.judge.usecase.PauseFightUseCase
import com.imuaythai.mtjudges.time.judge.usecase.ResumeFightUseCase
import javax.inject.Inject

class TimeJudgeViewModel @Inject constructor(
    private val fightRepository: FightRepository,
    private val pauseFightUseCase: PauseFightUseCase,
    private val resumeFightUseCase: ResumeFightUseCase
) : BaseViewModel(){

    val actionButtonText = MutableLiveData<String>()

    val isActionButtonEnabled = BooleanLiveData(false)

    val isStopButtonEnabled = BooleanLiveData(false)

    val isSignalButtonEnabled  = BooleanLiveData(false)

    init { }

    private var fightStatusDisposable = fightRepository.provideFightStatusObservable().subscribe { fightState ->

        when(fightState.state){
            FightState.WAITING -> {
                isActionButtonEnabled.postValue(false)
                isStopButtonEnabled.postValue(false)
                isSignalButtonEnabled.postValue(false)
                actionButtonText.postValue("START")
            }
            FightState.STARTED -> {
                isActionButtonEnabled.postValue(true)
                isStopButtonEnabled.postValue(true)
                isSignalButtonEnabled.postValue(false)
                actionButtonText.postValue("PAUSE")
            }
            FightState.PAUSED -> {
                isActionButtonEnabled.postValue(true)
                isStopButtonEnabled.postValue(false)
                isSignalButtonEnabled.postValue(false)
                actionButtonText.postValue("START")
            }
            FightState.STOPPED -> {
                isActionButtonEnabled.postValue(false)
                isStopButtonEnabled.postValue(false)
                isSignalButtonEnabled.postValue(false)
                actionButtonText.postValue("PAUSE")
            }
            FightState.BREAK -> {
                isActionButtonEnabled.postValue(false)
                isStopButtonEnabled.postValue(false)
                isSignalButtonEnabled.postValue(false)
                actionButtonText.postValue("PAUSE")
            }
            FightState.ENDED -> {
                isActionButtonEnabled.postValue(false)
                isStopButtonEnabled.postValue(false)
                isSignalButtonEnabled.postValue(false)
                actionButtonText.postValue("PAUSE")
            }
            else -> {
                isActionButtonEnabled.postValue(false)
                isStopButtonEnabled.postValue(false)
                isSignalButtonEnabled.postValue(false)
                actionButtonText.postValue("START")
            }
        }
    }

    fun onClickActionButton(){
        if(actionButtonText.value == "START"){
            execute(resumeFightUseCase,ResumeFightUseCase.Request()).subscribe(onSuccess = {},onError = {})
        } else {
            execute(pauseFightUseCase,PauseFightUseCase.Request()).subscribe(onSuccess = {},onError = {})
        }
    }

    fun onClickStopButton(){ }

    fun onClickSignalButton(){ }

    override fun onCleared() {
        super.onCleared()
        fightStatusDisposable.dispose()
    }

}