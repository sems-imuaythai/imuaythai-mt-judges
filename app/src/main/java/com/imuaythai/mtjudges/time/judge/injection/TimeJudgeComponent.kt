package com.imuaythai.mtjudges.time.judge.injection

import com.imuaythai.mtjudges.application.injection.scope.FragmentScope
import com.imuaythai.mtjudges.time.judge.TimeJudgeActivity
import dagger.Subcomponent

@FragmentScope
@Subcomponent( modules = [ TimeJudgeModule::class ] )
interface TimeJudgeComponent {

    fun inject(app: TimeJudgeActivity)

}