package com.imuaythai.mtjudges.provider.hubservice.injection

import com.imuaythai.mtjudges.provider.hubservice.HubService
import com.imuaythai.mtjudges.provider.hubservice.service.SignalRHubService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HubConnectionModule{

    @Provides @Singleton
    fun provideHubService(service: SignalRHubService): HubService = service

}


