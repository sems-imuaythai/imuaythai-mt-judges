package com.imuaythai.mtjudges.application

import android.app.Application
import android.content.Context
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.application.injection.ApplicationModule
import com.imuaythai.mtjudges.application.injection.DaggerApplicationComponent

class MTJudgesApplication : Application() {

    lateinit var applicationComponent : ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    companion object {
        fun get(context : Context) : MTJudgesApplication = context.applicationContext as MTJudgesApplication;
    }
}