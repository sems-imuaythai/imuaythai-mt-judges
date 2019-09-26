package com.imuaythai.mtjudges.knockout.card.model

import androidx.lifecycle.MutableLiveData
import java.util.ArrayList

class RadioGroupBehavior<LIVE_DATA:MutableLiveData<Boolean>> {

    private val fields = ArrayList<LIVE_DATA>()

    private val hasSelected = MutableLiveData<Boolean>()

    init {
        hasSelected.value = false
    }

    fun add( booleanLiveData: LIVE_DATA ): LIVE_DATA{
        fields.add(booleanLiveData)
        return booleanLiveData
    }

    fun hasSelected() = hasSelected

    fun check( comparator: (LIVE_DATA) -> Boolean ){
        val foundObject: LIVE_DATA? = fields.find(comparator)
        if(foundObject!=null){
            resetRatioState()
            foundObject.postValue(true)
            hasSelected.postValue(true)
        }
    }

    fun clearSelected(){
        resetRatioState()
        hasSelected.postValue(false)
    }

    fun getSelected() = fields.find(predicate = {
        it.value == true
    })

    private fun resetRatioState(){
        fields.forEach {
            it.postValue(false)
        }
    }

}