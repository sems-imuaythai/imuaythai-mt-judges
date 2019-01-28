package com.imuaythai.mtjudges.login.injection

import com.imuaythai.mtjudges.application.injection.scope.FragmentScope
import com.imuaythai.mtjudges.login.LoginActivity
import dagger.Subcomponent

@FragmentScope
@Subcomponent( modules = [ LoginModule::class ] )
interface LoginComponent {

    fun inject(app: LoginActivity)

}