package com.imuaythai.mtjudges.jury.judge.injection

import com.imuaythai.mtjudges.application.injection.scope.FragmentScope
import com.imuaythai.mtjudges.jury.judge.JuryJudgeActivity
import dagger.Subcomponent

@FragmentScope
@Subcomponent( modules = [ JuryJudgeModule::class ] )
interface JuryJudgeComponent {

    fun inject(activity: JuryJudgeActivity)

}