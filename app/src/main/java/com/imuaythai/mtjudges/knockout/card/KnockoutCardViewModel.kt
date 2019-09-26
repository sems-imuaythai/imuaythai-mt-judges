package com.imuaythai.mtjudges.knockout.card

import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.knockout.card.model.KnockoutButtonLiveData
import com.imuaythai.mtjudges.knockout.card.model.RadioGroupBehavior
import com.imuaythai.mtjudges.knockout.card.usecase.SendKnockoutTypeUseCase
import com.imuaythai.mtjudges.provider.dto.FighterType
import com.imuaythai.mtjudges.provider.dto.KnockOutType
import javax.inject.Inject

class KnockoutCardViewModel @Inject constructor(
    private val knockoutTypeRadio: RadioGroupBehavior<KnockoutButtonLiveData>,
    private val sendKnockoutTypeUseCase: SendKnockoutTypeUseCase
): BaseViewModel(){

    val isAcceptButtonEnabled = knockoutTypeRadio.hasSelected()

    val isINJRedButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.INJ,FighterType.RED))

    fun checkedRadioGroupButton(knockOutType: KnockOutType, fighterType: FighterType){
        knockoutTypeRadio.check(comparator = {
            it.knockOutType == knockOutType && it.fighterType == fighterType
        })
    }

    fun clickedSendButton(){
        val selected = knockoutTypeRadio.getSelected()

    }

}