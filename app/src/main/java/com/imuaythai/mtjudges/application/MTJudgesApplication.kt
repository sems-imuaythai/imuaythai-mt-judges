package com.imuaythai.mtjudges.application

import android.app.Application
import android.content.Context
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.application.injection.ApplicationModule
import com.imuaythai.mtjudges.application.injection.DaggerApplicationComponent
import com.imuaythai.mtjudges.application.injection.HubConnectionComponent
import com.imuaythai.mtjudges.provider.hubservice.dto.Ring
import com.imuaythai.mtjudges.provider.hubservice.injection.HubConnectionModule
import com.imuaythai.mtjudges.provider.webservice.injection.MTWebServiceModule

class MTJudgesApplication : Application() {

    lateinit var applicationComponent : ApplicationComponent
        private set

    var hubConnectionComponent : HubConnectionComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        initializeInjection();
    }

    private fun initializeInjection(){
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .mTWebServiceModule(MTWebServiceModule())
            .build()

        hubConnectionComponent = null;
    }

    fun createHubConnectionComponent(ring : Ring){
        hubConnectionComponent = applicationComponent.plus(HubConnectionModule(ring))
    }

    companion object {
        fun get(context : Context) : MTJudgesApplication = context.applicationContext as MTJudgesApplication;

        fun restartApplication(context : Context) = (context.applicationContext as MTJudgesApplication).initializeInjection()

    }


}