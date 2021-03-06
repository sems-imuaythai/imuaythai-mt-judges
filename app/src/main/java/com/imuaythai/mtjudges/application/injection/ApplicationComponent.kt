package com.imuaythai.mtjudges.application.injection

import com.imuaythai.mtjudges.change.settings.injection.ChangeSettingsComponent
import com.imuaythai.mtjudges.change.settings.injection.ChangeSettingsModule
import com.imuaythai.mtjudges.draw.card.injection.DrawCardComponent
import com.imuaythai.mtjudges.draw.card.injection.DrawCardModule
import com.imuaythai.mtjudges.fight.header.injection.FightHeaderComponent
import com.imuaythai.mtjudges.fight.header.injection.FightHeaderModule
import com.imuaythai.mtjudges.knockout.card.injection.KnockoutCardComponent
import com.imuaythai.mtjudges.knockout.card.injection.KnockoutCardModule
import com.imuaythai.mtjudges.main.judge.injection.MainJudgeComponent
import com.imuaythai.mtjudges.main.judge.injection.MainJudgeModule
import com.imuaythai.mtjudges.login.injection.LoginComponent
import com.imuaythai.mtjudges.login.injection.LoginModule
import com.imuaythai.mtjudges.point.judge.injection.PointJudgeComponent
import com.imuaythai.mtjudges.point.judge.injection.PointJudgeModule
import com.imuaythai.mtjudges.provider.hubservice.injection.HubConnectionModule
import com.imuaythai.mtjudges.provider.webservice.injection.WebServiceModule
import com.imuaythai.mtjudges.score.card.injection.ScoreCardComponent
import com.imuaythai.mtjudges.score.card.injection.ScoreCardModule
import com.imuaythai.mtjudges.settings.injection.SettingsComponent
import com.imuaythai.mtjudges.settings.injection.SettingsModule
import com.imuaythai.mtjudges.splash.SplashActivity
import com.imuaythai.mtjudges.time.judge.injection.TimeJudgeComponent
import com.imuaythai.mtjudges.time.judge.injection.TimeJudgeModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [ ApplicationModule::class, HubConnectionModule::class, WebServiceModule::class ] )
interface ApplicationComponent {

    fun plus( module : LoginModule ) : LoginComponent

    fun plus( module: TimeJudgeModule ) : TimeJudgeComponent

    fun plus( module: SettingsModule ) : SettingsComponent

    fun plus( module: ChangeSettingsModule ) : ChangeSettingsComponent

    fun plus(module: PointJudgeModule): PointJudgeComponent

    fun plus(module: FightHeaderModule): FightHeaderComponent

    fun plus(module: KnockoutCardModule): KnockoutCardComponent

    fun plus(module: ScoreCardModule): ScoreCardComponent

    fun plus(module: DrawCardModule): DrawCardComponent

    fun inject(activity: SplashActivity)

    fun plus(module: MainJudgeModule): MainJudgeComponent

}