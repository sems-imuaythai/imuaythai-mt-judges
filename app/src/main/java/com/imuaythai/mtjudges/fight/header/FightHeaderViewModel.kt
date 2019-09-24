package com.imuaythai.mtjudges.fight.header

import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.provider.dto.FightState
import com.imuaythai.mtjudges.service.FightRepository
import javax.inject.Inject

class FightHeaderViewModel @Inject constructor(
    private val fightRepository: FightRepository
): BaseViewModel(){

    val timerText: MutableLiveData<String> = MutableLiveData()

    val loggedUserSummaryText = fightRepository.getUserData().givenName + " " +
            fightRepository.getUserData().familyName + " (" +
            fightRepository.getUserRole().name + ")"

    val fightDurationSummaryText = fightRepository.getFightData().rounds.toString() + "x" + fightRepository.getFightData().roundDuration

    val weightCategorySummaryText = fightRepository.getFightData().weightCategory

    val refereeNameSummaryText: String = ""

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

    init {
        timerText.value = "00:01"
    }

    override fun onCleared() {
        super.onCleared()
        fightStatusDisposable.dispose()
    }
}