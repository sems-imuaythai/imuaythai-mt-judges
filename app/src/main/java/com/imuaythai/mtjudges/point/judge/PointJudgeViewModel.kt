package com.imuaythai.mtjudges.point.judge

import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.point.judge.model.PointCardType
import com.imuaythai.mtjudges.provider.dto.FightState
import com.imuaythai.mtjudges.service.FightRepository
import javax.inject.Inject

class PointJudgeViewModel @Inject constructor(
    private val fightRepository: FightRepository
) : BaseViewModel(){

    val pointCardType = MutableLiveData<PointCardType>()

    private var fightStatusDisposable = fightRepository.provideFightStatusObservable().subscribe { fightState ->
        when(fightState.state){
            FightState.STARTED,
            FightState.PAUSED,
            FightState.BREAK,
            FightState.WAITING -> {
                pointCardType.value = PointCardType.SCORE_CARD
            }
            FightState.STOPPED -> {
                pointCardType.value = PointCardType.KNOCKOUT_CARD
            }
            FightState.DRAW -> {
                pointCardType.value = PointCardType.DRAW_CARD
            }
            FightState.ENDED -> { }
            else -> { }
        }
    }

    override fun onCleared() {
        super.onCleared()
        fightStatusDisposable.dispose()
    }
}