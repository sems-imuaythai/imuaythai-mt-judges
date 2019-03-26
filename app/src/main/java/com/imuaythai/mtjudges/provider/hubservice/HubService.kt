package com.imuaythai.mtjudges.provider.hubservice

import androidx.lifecycle.LiveData
import com.imuaythai.mtjudges.provider.hubservice.dto.ConnectionState

interface HubService {

    fun connect(): LiveData<ConnectionState>

}