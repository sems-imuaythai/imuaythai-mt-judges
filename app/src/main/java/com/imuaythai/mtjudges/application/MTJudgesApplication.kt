package com.imuaythai.mtjudges.application

import android.app.Application
import android.content.Context
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.application.injection.ApplicationModule
import com.imuaythai.mtjudges.application.injection.DaggerApplicationComponent
import com.imuaythai.mtjudges.provider.hubservice.injection.HubConnectionModule
import java.security.SecureRandom
import javax.net.ssl.*

class MTJudgesApplication : Application() {

    lateinit var applicationComponent : ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        initializeInjection();


        HttpsURLConnection.setDefaultHostnameVerifier(NullHostNameVerifier())
        val context = SSLContext.getInstance("TLS")
        context.init(null, arrayOf<X509TrustManager>(NullX509TrustManager()), SecureRandom())
        HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        SocketFactory = context.getSocketFactory();
    }

    private fun initializeInjection(){
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .hubConnectionModule(HubConnectionModule())
            .build()
    }

    companion object {
        fun get(context : Context) : MTJudgesApplication = context.applicationContext as MTJudgesApplication;

        fun restartApplication(context : Context) = (context.applicationContext as MTJudgesApplication).initializeInjection()

        var SocketFactory: SSLSocketFactory? = null;
    }


}