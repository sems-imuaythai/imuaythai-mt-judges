package com.imuaythai.mtjudges.draw.card

import android.view.View
import androidx.lifecycle.Observer
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.common.live.BooleanLiveData
import com.imuaythai.mtjudges.common.live.VisibilityLiveData
import com.imuaythai.mtjudges.draw.card.model.DrawResultButtonLiveData
import com.imuaythai.mtjudges.draw.card.model.FighterTypeButtonLiveData
import com.imuaythai.mtjudges.draw.card.usecase.SendDrawResultUseCase
import com.imuaythai.mtjudges.knockout.card.model.RadioGroupBehavior
import com.imuaythai.mtjudges.provider.dto.DrawResultType
import com.imuaythai.mtjudges.provider.dto.FighterType
import com.imuaythai.mtjudges.service.FightRepository
import javax.inject.Inject

class DrawCardViewModel @Inject constructor(
    private val sendDrawResultUseCase: SendDrawResultUseCase,
    private val drawResultBehavior: RadioGroupBehavior<DrawResultButtonLiveData>,
    private val fighterTypeBehavior: RadioGroupBehavior<FighterTypeButtonLiveData>,
    private val fightRepository: FightRepository
): BaseViewModel(){

    val redFighterName = fightRepository.getFightData()?.redFighterName
    val blueFighterName = fightRepository.getFightData()?.blueFighterName

    val isBetterStyleChecked = drawResultBehavior.add(DrawResultButtonLiveData(DrawResultType.MOST_LEADING_OFF_OR_BETTER_STYLE))
    val isBetterDefenceChecked = drawResultBehavior.add(DrawResultButtonLiveData(DrawResultType.BETTER_DEFENCE))
    val isOtherChecked = drawResultBehavior.add(DrawResultButtonLiveData(DrawResultType.OTHER))

    val isRedFighterChecked = fighterTypeBehavior.add(FighterTypeButtonLiveData(FighterType.RED))
    val isBlueFighterChecked = fighterTypeBehavior.add(FighterTypeButtonLiveData(FighterType.BLUE))

    val screenLoadingVisibility = VisibilityLiveData(View.GONE)

    val isAcceptButtonEnabled = BooleanLiveData(false)

    init {
        drawResultBehavior.hasSelected().observeForever(FieldStateObserver())
        fighterTypeBehavior.hasSelected().observeForever(FieldStateObserver())
    }

    fun checkedRadioGroupButton(drawResultType: DrawResultType, checked: Boolean){
        if(checked) {
            drawResultBehavior.check(comparator = {
                it.drawResultType == drawResultType
            })
        }
    }

    fun checkedRadioGroupButton(fighterType: FighterType, checked: Boolean){
        if(checked) {
            fighterTypeBehavior.check(comparator = {
                it.fighterType == fighterType
            })
        }
    }

    fun clickedSendResult(){
        val selectedDrawResult = drawResultBehavior.getSelected()
        val selectedFighterType = fighterTypeBehavior.getSelected()
        if(selectedFighterType !=null && selectedDrawResult!= null) {
            val request = SendDrawResultUseCase.Request(
                selectedDrawResult.drawResultType,
                selectedFighterType.fighterType
            )
            screenLoadingVisibility.setVisible()
            execute(sendDrawResultUseCase, request).subscribe(onSuccess = {
                screenLoadingVisibility.setGone()
            }, onError = {
                screenLoadingVisibility.setGone()
            })
        }
    }

    internal inner class FieldStateObserver: Observer<Boolean>{
        override fun onChanged(t: Boolean?) {
            isAcceptButtonEnabled.postValue(
                fighterTypeBehavior.hasSelected().value == true
                    && drawResultBehavior.hasSelected().value == true
            )
        }
    }

}