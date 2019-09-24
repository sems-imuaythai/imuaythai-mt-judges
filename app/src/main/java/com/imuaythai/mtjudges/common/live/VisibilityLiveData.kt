package com.imuaythai.mtjudges.common.live

import android.view.View
import androidx.lifecycle.MutableLiveData

class VisibilityLiveData constructor(initValue: Int): MutableLiveData<Int>() {

    init {
        value = initValue
    }

    fun setVisible(){
        postValue(View.VISIBLE)
    }

    fun setInvisible(){
        postValue(View.INVISIBLE)
    }

    fun setGone(){
        postValue(View.GONE)
    }

}