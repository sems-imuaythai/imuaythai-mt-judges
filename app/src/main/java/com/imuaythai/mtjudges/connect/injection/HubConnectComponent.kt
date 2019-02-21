package com.imuaythai.mtjudges.connect.injection

import com.imuaythai.mtjudges.application.injection.scope.FragmentScope
import com.imuaythai.mtjudges.connect.HubConnectActivity
import com.imuaythai.mtjudges.login.LoginActivity
import dagger.Subcomponent

@FragmentScope
@Subcomponent( modules = [ HubConnectModule::class ] )
interface HubConnectComponent {

    fun inject(app: HubConnectActivity)

}