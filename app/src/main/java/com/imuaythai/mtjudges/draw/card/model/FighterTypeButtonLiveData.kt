package com.imuaythai.mtjudges.draw.card.model

import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.provider.dto.FighterType

class FighterTypeButtonLiveData constructor(
    val fighterType: FighterType
): MutableLiveData<Boolean>() {

    init {
        value = false
    }

}