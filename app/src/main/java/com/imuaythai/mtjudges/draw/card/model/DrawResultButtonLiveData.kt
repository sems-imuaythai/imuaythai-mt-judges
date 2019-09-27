package com.imuaythai.mtjudges.draw.card.model

import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.provider.dto.DrawResultType

class DrawResultButtonLiveData constructor(
    val drawResultType: DrawResultType
): MutableLiveData<Boolean>() {

    init {
        value = false
    }

}