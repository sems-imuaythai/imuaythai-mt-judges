package com.imuaythai.mtjudges.connect

import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.provider.hubservice.service.HubConnectionService
import javax.inject.Inject

class HubConnectViewModel @Inject constructor(
    private val connectionService: HubConnectionService
): BaseViewModel(){

    init {

    }

}