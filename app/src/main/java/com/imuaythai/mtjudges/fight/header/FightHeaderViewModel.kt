package com.imuaythai.mtjudges.fight.header

import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.provider.dto.FightState
import com.imuaythai.mtjudges.service.FightRepository
import java.lang.StringBuilder
import java.util.*
import javax.inject.Inject

class FightHeaderViewModel @Inject constructor(
    private val fightRepository: FightRepository
): BaseViewModel(){

    val timerText: MutableLiveData<String> = MutableLiveData()

    val loggedUserSummaryText = fightRepository.getUserData().givenName + " " +
            fightRepository.getUserData().familyName + " (" +
            fightRepository.getUserRole().name + ")"

    val fightDurationSummaryText = fightRepository.getFightData()?.rounds.toString() + "x" + fightRepository.getFightData()?.roundDuration

    val weightCategorySummaryText = fightRepository.getFightData()?.weightCategory

    val refereeNameSummaryText: String = ""

    private var timerMillis: Long = 0

    private val periodMillis: Long = 100

    private var timer: Timer = Timer()

    private var currentFightState: FightState = FightState.WAITING

    private var fightStatusDisposable = fightRepository.provideFightStatusObservable().subscribe { fightState ->
        if(currentFightState != fightState.state) {
            currentFightState = fightState.state
            when (fightState.state) {
                FightState.WAITING -> {
                    timerText.value = "WAITING"
                    timer.cancel()
                }
                FightState.STARTED -> {
                    timerMillis = fightState.time
                    timer.purge()
                    timer = Timer()
                    timer.scheduleAtFixedRate(UpdateTimeTask(), 0, periodMillis)
                }
                FightState.PAUSED -> {
                    timer.cancel()
                }
                FightState.STOPPED -> {
                    timer.cancel()
                }
                FightState.BREAK -> {
                    timerMillis = fightState.time
                    timer.purge()
                    timer = Timer()
                    timer.scheduleAtFixedRate(UpdateTimeTask(), 0, periodMillis)
                }
                FightState.ENDED -> {
                    timer.cancel()
                }
                else -> {
                    timer.cancel()
                }
            }
        }
    }

    internal inner class UpdateTimeTask : TimerTask() {

        override fun run() {
            timerMillis -= periodMillis

            val minutes = (timerMillis/1000)/60
            val seconds = (timerMillis/1000)%60

            val sb = StringBuilder()
            if(minutes < 10) {
                sb.append("0")
            }
            sb.append(minutes.toString())
            sb.append(":")
            if(seconds < 10){
                sb.append("0")
            }
            sb.append(seconds.toString())
            timerText.postValue(sb.toString())
        }
    }

    override fun onCleared() {
        super.onCleared()
        timer.purge()
        fightStatusDisposable.dispose()
    }

}