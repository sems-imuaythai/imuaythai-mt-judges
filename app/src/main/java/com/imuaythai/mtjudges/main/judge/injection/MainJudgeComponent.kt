package com.imuaythai.mtjudges.main.judge.injection

import com.imuaythai.mtjudges.application.injection.scope.FragmentScope
import com.imuaythai.mtjudges.main.judge.MainJudgeActivity
import dagger.Subcomponent

@FragmentScope
@Subcomponent( modules = [ MainJudgeModule::class ] )
interface MainJudgeComponent {

    fun inject(activity: MainJudgeActivity)

}