package com.imuaythai.mtjudges.knockout.card.model

import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.provider.dto.KnockOutType
import com.imuaythai.mtjudges.provider.dto.FighterType

class KnockoutButtonLiveData constructor(
    val knockOutType: KnockOutType,
    val fighterType: FighterType
): MutableLiveData<Boolean>() {

    init {
        value = false
    }



}