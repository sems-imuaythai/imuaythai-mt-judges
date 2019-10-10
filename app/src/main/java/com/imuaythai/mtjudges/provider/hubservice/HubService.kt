package com.imuaythai.mtjudges.provider.hubservice

import androidx.lifecycle.LiveData
import com.imuaythai.mtjudges.provider.hubservice.dto.ConnectionState

interface HubService {

    fun connectionState(): LiveData<ConnectionState>

    fun connect()

    fun join(fightId: Int)

}