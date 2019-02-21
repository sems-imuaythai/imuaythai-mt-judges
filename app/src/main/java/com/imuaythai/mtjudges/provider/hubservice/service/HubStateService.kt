package com.imuaythai.mtjudges.provider.hubservice.service

import androidx.lifecycle.LiveData
import com.imuaythai.mtjudges.provider.hubservice.dto.ConnectionState

interface HubStateService {

    fun provideConnectionState() : LiveData<ConnectionState>

}