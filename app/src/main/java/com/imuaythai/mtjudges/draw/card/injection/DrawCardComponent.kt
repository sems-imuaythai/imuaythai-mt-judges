package com.imuaythai.mtjudges.draw.card.injection

import com.imuaythai.mtjudges.application.injection.scope.FragmentScope
import com.imuaythai.mtjudges.draw.card.DrawCardFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent( modules = [ DrawCardModule::class ] )
interface DrawCardComponent {

    fun inject(fragment: DrawCardFragment)

}