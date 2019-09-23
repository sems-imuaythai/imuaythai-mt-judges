package com.imuaythai.mtjudges.common.live

import androidx.lifecycle.MutableLiveData

class PointLiveData constructor(): MutableLiveData<Int>() {

    init {
        value = 0
    }

    fun increment(){
        postValue(value!!.plus(1))
    }

    fun decrement(){
        postValue(Math.max(value!!.minus(1),0))
    }

}