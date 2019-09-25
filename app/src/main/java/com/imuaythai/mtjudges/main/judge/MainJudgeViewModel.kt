package com.imuaythai.mtjudges.main.judge

import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.provider.dto.FightState
import com.imuaythai.mtjudges.service.FightRepository
import javax.inject.Inject

class MainJudgeViewModel @Inject constructor(
    private val fightRepository: FightRepository
) : BaseViewModel(){

    private var fightStatusDisposable = fightRepository.provideFightStatusObservable().subscribe { fightState ->

        when(fightState.state){
            FightState.WAITING -> {

            }
            FightState.STARTED -> {

            }
            FightState.PAUSED -> {

            }
            FightState.STOPPED -> {

            }
            FightState.BREAK -> {

            }
            FightState.ENDED -> {

            }
            else -> {

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        fightStatusDisposable.dispose()
    }

}