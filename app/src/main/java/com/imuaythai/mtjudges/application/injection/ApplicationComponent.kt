package com.imuaythai.mtjudges.application.injection

import com.imuaythai.mtjudges.login.injection.LoginComponent
import com.imuaythai.mtjudges.login.injection.LoginModule
import com.imuaythai.mtjudges.main.MainActivity
import com.imuaythai.mtjudges.provider.injection.MTWebServiceModule
import com.imuaythai.mtjudges.splash.SplashActivity
import com.imuaythai.mtjudges.time.judge.injection.TimeJudgeComponent
import com.imuaythai.mtjudges.time.judge.injection.TimeJudgeModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [ ApplicationModule::class, MTWebServiceModule::class ] )
interface ApplicationComponent {

    fun plus( loginModule : LoginModule ) : LoginComponent

    fun plus( timeJudgeModule: TimeJudgeModule ) : TimeJudgeComponent

    fun inject(activity: MainActivity)

    fun inject(activity: SplashActivity)

}