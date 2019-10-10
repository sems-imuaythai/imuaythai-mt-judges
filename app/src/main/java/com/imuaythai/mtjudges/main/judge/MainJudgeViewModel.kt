package com.imuaythai.mtjudges.main.judge

import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.common.live.BooleanLiveData
import com.imuaythai.mtjudges.main.judge.usecase.AcceptResultsUseCase
import com.imuaythai.mtjudges.main.judge.usecase.ResetFightUseCase
import com.imuaythai.mtjudges.main.judge.usecase.StartRoundUseCase
import com.imuaythai.mtjudges.provider.dto.FightState
import com.imuaythai.mtjudges.service.FightRepository
import javax.inject.Inject

class MainJudgeViewModel @Inject constructor(
    private val fightRepository: FightRepository,
    private val acceptResultsUseCase: AcceptResultsUseCase,
    private val resetFightUseCase: ResetFightUseCase,
    private val startRoundUseCase: StartRoundUseCase
) : BaseViewModel(){

    val isScreenLoading : MutableLiveData<Boolean> = MutableLiveData()

    val redFighterName = fightRepository.getFightData()?.redFighterName
    val blueFighterName = fightRepository.getFightData()?.blueFighterName

    val isStartRoundButtonEnabled = BooleanLiveData(false)
    val isAcceptResultsButtonEnabled = BooleanLiveData(false)

    private var fightStatusDisposable = fightRepository.provideFightStatusObservable().subscribe { fightState ->

        when(fightState.state){
            FightState.WAITING -> {
                isStartRoundButtonEnabled.postValue(true)
                isAcceptResultsButtonEnabled.postValue(false)
            }
            FightState.STARTED -> {
                isStartRoundButtonEnabled.postValue(false)
                isAcceptResultsButtonEnabled.postValue(false)
            }
            FightState.PAUSED -> {
                isStartRoundButtonEnabled.postValue(false)
                isAcceptResultsButtonEnabled.postValue(false)
            }
            FightState.STOPPED -> {
                isStartRoundButtonEnabled.postValue(false)
                isAcceptResultsButtonEnabled.postValue(false)
            }
            FightState.ACCEPTED -> {
                isStartRoundButtonEnabled.postValue(true)
                isAcceptResultsButtonEnabled.postValue(false)
            }
            FightState.BREAK -> {
                isStartRoundButtonEnabled.postValue(false)
                isAcceptResultsButtonEnabled.postValue(true)
            }
            FightState.ENDED -> {
                isStartRoundButtonEnabled.postValue(false)
                isAcceptResultsButtonEnabled.postValue(false)
            }
            else -> {
                isStartRoundButtonEnabled.postValue(false)
                isAcceptResultsButtonEnabled.postValue(false)
            }
        }
    }

    fun acceptResults(){
        val request = AcceptResultsUseCase.Request()
        execute(acceptResultsUseCase,request).withLoader(isScreenLoading).subscribe(onSuccess = { response ->

        },onError = {

        })
    }

    fun startRound(){
        val request = StartRoundUseCase.Request()
        execute(startRoundUseCase,request).withLoader(isScreenLoading).subscribe(onSuccess = { response ->

        },onError = {

        })
    }

    fun resetFight(){
        val request = ResetFightUseCase.Request()
        execute(resetFightUseCase,request).withLoader(isScreenLoading).subscribe(onSuccess = { response ->

        },onError = {

        })
    }

    override fun onCleared() {
        super.onCleared()
        fightStatusDisposable.dispose()
    }

}