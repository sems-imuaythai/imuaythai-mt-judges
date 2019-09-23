package com.imuaythai.mtjudges.common.live

import androidx.lifecycle.MutableLiveData

class BooleanLiveData constructor(initValue: Boolean): MutableLiveData<Boolean>() {

    init {
        value = initValue
    }

    fun setTrue(){
        postValue(true)
    }

    fun setFalse(){
        postValue(false)
    }

    fun invert(){
        postValue(!value!!)
    }

}