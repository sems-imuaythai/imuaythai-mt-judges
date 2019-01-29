package com.imuaythai.mtjudges.point.judge.injection

import com.imuaythai.mtjudges.application.injection.scope.FragmentScope
import com.imuaythai.mtjudges.point.judge.PointJudgeActivity
import com.imuaythai.mtjudges.time.judge.TimeJudgeActivity
import dagger.Subcomponent

@FragmentScope
@Subcomponent( modules = [ PointJudgeModule::class ] )
interface PointJudgeComponent {

    fun inject(activity: PointJudgeActivity)

}