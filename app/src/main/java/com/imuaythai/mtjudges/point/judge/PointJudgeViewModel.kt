package com.imuaythai.mtjudges.point.judge

import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.common.live.BooleanLiveData
import com.imuaythai.mtjudges.common.live.PointLiveData
import com.imuaythai.mtjudges.point.judge.usecase.SendRoundPointsUseCase
import javax.inject.Inject

class PointJudgeViewModel @Inject constructor(
    private val sendRoundPointsUseCase: SendRoundPointsUseCase
) : BaseViewModel(){

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

    init {
        resetData()
    }

    fun setRedPoints(value: Int){
        if(value >= 5) {
            redPoints = value
            isRedAcceptEnabled.setTrue()
            isRedAcceptChecked.setFalse()
        }
    }

    fun setBluePoints(value: Int){
        if(value >= 5) {
            bluePoints = value
            isBlueAcceptEnabled.setTrue()
            isBlueAcceptChecked.setFalse()
        }
    }

    fun sendAcceptedPoints() {
        val requestObject = SendRoundPointsUseCase.Request("")
        execute(sendRoundPointsUseCase,requestObject).subscribe(onSuccess = {
            resetData()
        }, onError = {
            resetData()
            it.printStackTrace()
        })
    }

    private fun resetData(){
        redC.value = 0
        redPoints = 0
        bluePoints = 0
        isRedAcceptEnabled.setFalse()
    }

}