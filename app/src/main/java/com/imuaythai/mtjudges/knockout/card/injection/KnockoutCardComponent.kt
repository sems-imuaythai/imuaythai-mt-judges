package com.imuaythai.mtjudges.knockout.card.injection

import com.imuaythai.mtjudges.application.injection.scope.FragmentScope
import com.imuaythai.mtjudges.knockout.card.KnockoutCardFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent( modules = [ KnockoutCardModule::class ] )
interface KnockoutCardComponent {

    fun inject(activity: KnockoutCardFragment)

}