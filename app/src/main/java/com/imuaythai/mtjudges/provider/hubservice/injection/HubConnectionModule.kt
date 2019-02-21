package com.imuaythai.mtjudges.provider.hubservice.injection

import com.imuaythai.mtjudges.application.injection.scope.HubConnectionScope
import com.imuaythai.mtjudges.provider.hubservice.HubService
import com.imuaythai.mtjudges.provider.hubservice.service.HubStateService
import com.imuaythai.mtjudges.provider.hubservice.service.HubTimeKeeperService
import com.imuaythai.mtjudges.provider.hubservice.dto.Ring
import com.imuaythai.mtjudges.provider.hubservice.impl.HubConnectionServiceImpl
import com.imuaythai.mtjudges.provider.hubservice.service.HubConnectionService
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HubConnectionModule constructor(
    private val ring: Ring
){

    @Provides
    @HubConnectionScope
    fun provideHubConnection() : HubConnection = HubConnectionBuilder
        .create("ws://server.com/ring/" + ring.name )
        .shouldSkipNegotiate(false)
        .withHandshakeResponseTimeout(10000)
        .build()

    @Provides
    @HubConnectionScope
    fun provideHubService(service: HubConnectionServiceImpl) : HubService = service

    @Provides
    fun provideHubConnectionService(service: HubService) : HubConnectionService = service

    @Provides
    fun provideHubTimeKeeperService(service: HubService) : HubTimeKeeperService = service

    @Provides
    fun provideHubStateService(service: HubService) : HubStateService = service
}