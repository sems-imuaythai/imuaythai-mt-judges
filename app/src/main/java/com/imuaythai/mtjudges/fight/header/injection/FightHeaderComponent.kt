package com.imuaythai.mtjudges.fight.header.injection

import com.imuaythai.mtjudges.application.injection.scope.FragmentScope
import com.imuaythai.mtjudges.fight.header.FightHeaderFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent( modules = [ FightHeaderModule::class ] )
interface FightHeaderComponent {

    fun inject(fragment: FightHeaderFragment)

}