package com.imuaythai.mtjudges.knockout.card

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.common.live.VisibilityLiveData
import com.imuaythai.mtjudges.knockout.card.model.KnockoutButtonLiveData
import com.imuaythai.mtjudges.knockout.card.model.RadioGroupBehavior
import com.imuaythai.mtjudges.knockout.card.usecase.SendKnockoutTypeUseCase
import com.imuaythai.mtjudges.provider.dto.FighterType
import com.imuaythai.mtjudges.provider.dto.KnockOutType
import com.imuaythai.mtjudges.service.FightRepository
import javax.inject.Inject

class KnockoutCardViewModel @Inject constructor(
    private val knockoutTypeRadio: RadioGroupBehavior<KnockoutButtonLiveData>,
    private val sendKnockoutTypeUseCase: SendKnockoutTypeUseCase,
    private val fightRepository: FightRepository
): BaseViewModel(){

    val redFighterName = fightRepository.getFightData().redFighterName
    val blueFighterName = fightRepository.getFightData().blueFighterName

    val isKoHeadRedButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.KO_HEAD,FighterType.RED))
    val isKoBodyRedButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.KO_BODY,FighterType.RED))
    val isRscInjRedButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.RSC_INJ,FighterType.RED))
    val isRscCcltRedButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.RSC_CCLT,FighterType.RED))
    val isRscInjHeadRedButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.RSC_INJ_HEAD,FighterType.RED))
    val isRscInjBodyRedButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.RSC_INJ_BODY,FighterType.RED))
    val isRscOutclassBodyRedButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.RSC_OUTCLASS,FighterType.RED))
    val isNoContestRedButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.NO_CONTEST,FighterType.RED))
    val isRetRedButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.RET,FighterType.RED))
    val isWoRedButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.WO,FighterType.RED))

    val isKoHeadBlueButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.KO_HEAD,FighterType.BLUE))
    val isKoBodyBlueButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.KO_BODY,FighterType.BLUE))
    val isRscInjBlueButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.RSC_INJ,FighterType.BLUE))
    val isRscCcltBlueButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.RSC_CCLT,FighterType.BLUE))
    val isRscInjHeadBlueButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.RSC_INJ_HEAD,FighterType.BLUE))
    val isRscInjBodyBlueButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.RSC_INJ_BODY,FighterType.BLUE))
    val isRscOutclassBodyBlueButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.RSC_OUTCLASS,FighterType.BLUE))
    val isNoContestBlueButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.NO_CONTEST,FighterType.BLUE))
    val isRetBlueButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.RET,FighterType.BLUE))
    val isWoBlueButtonChecked = knockoutTypeRadio.add(KnockoutButtonLiveData(KnockOutType.WO,FighterType.BLUE))

    val screenLoadingVisibility = VisibilityLiveData(View.GONE)

    val isAcceptButtonEnabled = knockoutTypeRadio.hasSelected()

    fun checkedRadioGroupButton(knockOutType: KnockOutType, fighterType: FighterType, checked: Boolean){
        if(checked) {
            knockoutTypeRadio.check(comparator = {
                it.knockOutType == knockOutType && it.fighterType == fighterType
            })
        }
    }

    fun clickedSendButton(){
        val selected = knockoutTypeRadio.getSelected()
        if(selected != null) {
            val request = SendKnockoutTypeUseCase.Request(selected.knockOutType, selected.fighterType)
            screenLoadingVisibility.setVisible()
            execute(sendKnockoutTypeUseCase, request).subscribe(onSuccess = {
                    screenLoadingVisibility.setGone()
                }, onError = {
                    screenLoadingVisibility.setGone()
                })
        }
    }

}