package com.imuaythai.mtjudges.application.injection

import com.imuaythai.mtjudges.login.injection.LoginComponent
import com.imuaythai.mtjudges.login.injection.LoginModule
import com.imuaythai.mtjudges.provider.injection.MTWebServiceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [ ApplicationModule::class, MTWebServiceModule::class ] )
interface ApplicationComponent {

    fun plus( loginModule : LoginModule ) : LoginComponent

}