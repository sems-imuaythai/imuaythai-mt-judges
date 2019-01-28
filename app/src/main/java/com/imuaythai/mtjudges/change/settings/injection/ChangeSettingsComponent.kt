package com.imuaythai.mtjudges.change.settings.injection

import com.imuaythai.mtjudges.application.injection.scope.FragmentScope
import com.imuaythai.mtjudges.change.settings.ChangeSettingsActivity
import dagger.Subcomponent

@FragmentScope
@Subcomponent( modules = [ ChangeSettingsModule::class ] )
interface ChangeSettingsComponent {

    fun inject(app: ChangeSettingsActivity)

}