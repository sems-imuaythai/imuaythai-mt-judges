package com.imuaythai.mtjudges.score.card

import android.view.View
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.common.live.BooleanLiveData
import com.imuaythai.mtjudges.common.live.PointLiveData
import com.imuaythai.mtjudges.common.live.VisibilityLiveData
import com.imuaythai.mtjudges.score.card.usecase.SendRoundPointsUseCase
import com.imuaythai.mtjudges.provider.dto.AddRingFightPointsDto
import com.imuaythai.mtjudges.provider.dto.FightPointsDto
import com.imuaythai.mtjudges.provider.dto.FightState
import com.imuaythai.mtjudges.service.FightRepository
import javax.inject.Inject

class ScoreCardViewModel @Inject constructor(
    private val sendRoundPointsUseCase: SendRoundPointsUseCase,
    private val fightRepository: FightRepository
): BaseViewModel(){

    val redFighterName = fightRepository.getFightData().redFighterName
    val blueFighterName = fightRepository.getFightData().blueFighterName

    val redC = PointLiveData()
    val redKC = PointLiveData()
    val redW = PointLiveData()
    val redJ = PointLiveData()
    val redX = PointLiveData()

    val blueC = PointLiveData()
    val blueKC = PointLiveData()
    val blueW = PointLiveData()
    val blueJ = PointLiveData()
    val blueX = PointLiveData()

    private var redPoints = 0
    private var bluePoints = 0

    val isRedAcceptEnabled = BooleanLiveData(false)
    val isBlueAcceptEnabled = BooleanLiveData(false)

    val isPointButtonsEnabled = BooleanLiveData(false)

    val screenLoadingVisibility = VisibilityLiveData(View.VISIBLE)

    val scoreCardVisibility = VisibilityLiveData(View.GONE)

    val isRedAcceptChecked = BooleanLiveData(false)
    val isBlueAcceptChecked = BooleanLiveData(false)

    fun onClickMinusRedC() = redC.decrement()
    fun onClickPlusRedC() = redC.increment()
    fun onClickMinusRedKC() = redKC.decrement()
    fun onClickPlusRedKC() = redKC.increment()
    fun onClickMinusRedW() = redW.decrement()
    fun onClickPlusRedW() = redW.increment()
    fun onClickMinusRedJ() = redJ.decrement()
    fun onClickPlusRedJ() = redJ.increment()
    fun onClickMinusRedX() = redX.decrement()
    fun onClickPlusRedX() = redX.increment()

    fun onClickMinusBlueC() = blueC.decrement()
    fun onClickPlusBlueC() = blueC.increment()
    fun onClickMinusBlueKC() = blueKC.decrement()
    fun onClickPlusBlueKC() = blueKC.increment()
    fun onClickMinusBlueW() = blueW.decrement()
    fun onClickPlusBlueW() = blueW.increment()
    fun onClickMinusBlueJ() = blueJ.decrement()
    fun onClickPlusBlueJ() = blueJ.increment()
    fun onClickMinusBlueX() = blueX.decrement()
    fun onClickPlusBlueX() = blueX.increment()

    private var roundNumber: Int = 0

    private var fightStatusDisposable = fightRepository.provideFightStatusObservable().subscribe { fightState ->
        roundNumber = fightState.roundNum
        when(fightState.state){
            FightState.WAITING -> {
                isPointButtonsEnabled.setFalse()
                scoreCardVisibility.setGone()
                screenLoadingVisibility.setGone()
            }
            FightState.STARTED -> {
                isPointButtonsEnabled.setTrue()
                scoreCardVisibility.setGone()
                screenLoadingVisibility.setGone()
            }
            FightState.PAUSED -> {
                isPointButtonsEnabled.setFalse()
                scoreCardVisibility.setGone()
                screenLoadingVisibility.setGone()
            }
            FightState.STOPPED -> {
                isPointButtonsEnabled.setFalse()
                scoreCardVisibility.setGone()
                screenLoadingVisibility.setGone()
            }
            FightState.BREAK -> {
                isPointButtonsEnabled.setFalse()
                scoreCardVisibility.setVisible()
                screenLoadingVisibility.setGone()
            }
            FightState.ENDED -> {
                isPointButtonsEnabled.setFalse()
                scoreCardVisibility.setGone()
                screenLoadingVisibility.setGone()
            }
            else -> {
                screenLoadingVisibility.setVisible()
            }
        }
    }

    fun setRedPoints(value: Int){
        if(value >= 5) {
            redPoints = value
            isRedAcceptEnabled.setTrue()
        } else {
            isRedAcceptEnabled.setFalse()
        }
        isRedAcceptChecked.setFalse()
    }

    fun setBluePoints(value: Int){
        if(value >= 5) {
            bluePoints = value
            isBlueAcceptEnabled.setTrue()
        } else {
            isBlueAcceptEnabled.setFalse()
        }
        isBlueAcceptChecked.setFalse()
    }

    fun sendAcceptedPoints() {

        val redPointsDto = FightPointsDto(
            fighterId = fightRepository.getFightData().redFighterId,
            C = redC.value?:0,
            KO = redKC.value?:0,
            W = redW.value?:0,
            J = redJ.value?:0,
            X = redX.value?:0,
            points = redPoints
        )

        val bluePointsDto = FightPointsDto(
            fighterId = fightRepository.getFightData().blueFighterId,
            C = blueC.value?:0,
            KO = blueKC.value?:0,
            W = blueW.value?:0,
            J = blueJ.value?:0,
            X = blueX.value?:0,
            points = bluePoints
        )

        val requestObject = SendRoundPointsUseCase.Request(
            AddRingFightPointsDto(
                roundNumber,
                redPointsDto,
                bluePointsDto
            )
        )

        screenLoadingVisibility.setVisible()
        execute(sendRoundPointsUseCase,requestObject).subscribe(onSuccess = {
            screenLoadingVisibility.setGone()
        }, onError = {
            screenLoadingVisibility.setGone()
            it.printStackTrace()
        })
    }

    private fun resetData(){
        redC.value = 0
        redKC.value = 0
        redW.value = 0
        redJ.value = 0
        redX.value = 0
        blueC.value = 0
        blueKC.value = 0
        blueW.value = 0
        blueJ.value = 0
        blueX.value = 0
        redPoints = 0
        bluePoints = 0
        isRedAcceptEnabled.setFalse()
        isBlueAcceptEnabled.setFalse()
        scoreCardVisibility.setGone()
        isRedAcceptChecked.setFalse()
        isBlueAcceptChecked.setFalse()
        isPointButtonsEnabled.setFalse()
    }

    override fun onCleared() {
        super.onCleared()
        fightStatusDisposable.dispose()
    }

}