package com.imuaythai.mtjudges.application.injection

import com.imuaythai.mtjudges.application.injection.scope.HubConnectionScope
import com.imuaythai.mtjudges.connect.injection.HubConnectComponent
import com.imuaythai.mtjudges.connect.injection.HubConnectModule
import com.imuaythai.mtjudges.provider.hubservice.injection.HubConnectionModule
import dagger.Subcomponent

@HubConnectionScope
@Subcomponent( modules = [ HubConnectionModule::class ] )
interface HubConnectionComponent {

    fun plus(module: HubConnectModule): HubConnectComponent

}