package com.imuaythai.mtjudges.provider.hubservice

import androidx.lifecycle.LiveData
import com.imuaythai.mtjudges.provider.hubservice.dto.FightDescription

interface HubProvider{

    fun fightDescriptionObservable(): LiveData<FightDescription>

}