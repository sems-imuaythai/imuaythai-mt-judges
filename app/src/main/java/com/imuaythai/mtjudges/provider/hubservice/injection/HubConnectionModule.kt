package com.imuaythai.mtjudges.provider.hubservice.injection

import com.imuaythai.mtjudges.provider.hubservice.HubService
import com.imuaythai.mtjudges.provider.hubservice.handler.*
import com.imuaythai.mtjudges.provider.hubservice.protocol.HubCommand
import com.imuaythai.mtjudges.provider.hubservice.protocol.HubCommandHandler
import com.imuaythai.mtjudges.provider.hubservice.protocol.HubCommandKey
import com.imuaythai.mtjudges.provider.hubservice.service.SignalRHubService
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
class HubConnectionModule{

    @Provides @Singleton
    fun provideHubService(service: SignalRHubService): HubService = service

    @Provides @IntoMap
    @HubCommandKey(HubCommand.FightStateChanged)
    fun provideFightStateChanged(handler: FightStateChangedHandler): HubCommandHandler = handler

}


