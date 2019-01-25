package com.imuaythai.mtjudges.settings.injection

import com.imuaythai.mtjudges.application.injection.scope.FragmentScope
import com.imuaythai.mtjudges.settings.SettingsActivity
import dagger.Subcomponent

@FragmentScope
@Subcomponent( modules = [ SettingsModule::class ] )
interface SettingsComponent {

    fun inject(app: SettingsActivity)

}