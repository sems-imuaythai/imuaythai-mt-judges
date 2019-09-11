package com.imuaythai.mtjudges.provider.hubservice

import androidx.lifecycle.LiveData
import com.imuaythai.mtjudges.provider.hubservice.dto.ConnectionState
import com.imuaythai.mtjudges.provider.hubservice.dto.FightDescription

interface HubService {

    fun connect(): LiveData<ConnectionState>



}