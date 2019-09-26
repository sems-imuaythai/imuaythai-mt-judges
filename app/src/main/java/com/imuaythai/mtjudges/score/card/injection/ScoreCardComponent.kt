package com.imuaythai.mtjudges.score.card.injection

import com.imuaythai.mtjudges.application.injection.scope.FragmentScope
import com.imuaythai.mtjudges.knockout.card.KnockoutCardFragment
import com.imuaythai.mtjudges.score.card.ScoreCardFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent( modules = [ ScoreCardModule::class ] )
interface ScoreCardComponent {

    fun inject(fragment: ScoreCardFragment)

}