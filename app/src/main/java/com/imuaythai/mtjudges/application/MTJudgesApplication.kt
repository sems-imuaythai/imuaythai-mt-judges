package com.imuaythai.mtjudges.application

import android.app.Application
import android.content.Context
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.application.injection.ApplicationModule
import com.imuaythai.mtjudges.application.injection.DaggerApplicationComponent
import com.imuaythai.mtjudges.provider.hubservice.dto.Ring
import com.imuaythai.mtjudges.provider.webservice.injection.MTWebServiceModule
import java.security.SecureRandom
import javax.net.ssl.*

class MTJudgesApplication : Application() {

    lateinit var applicationComponent : ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        initializeInjection();


        /*val cf = CertificateFactory.getInstance("X.509")
        val caInput = resources.openRawResource(R.raw.localhost)
        val ca = cf.generateCertificate(caInput)
        println("ca=" + (ca as X509Certificate).subjectDN)

        val keyStoreType = KeyStore.getDefaultType()
        val keyStore = KeyStore.getInstance(keyStoreType)
        keyStore.load(null, null)
        keyStore.setCertificateEntry("ca", ca)

        val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
        val tmf = TrustManagerFactory.getInstance(tmfAlgorithm)
        tmf.init(keyStore)

        val context = SSLContext.getInstance("TLS")
        context.init(null, tmf.trustManagers, null)
        HttpsURLConnection.setDefaultSSLSocketFactory(context.socketFactory)*/

        HttpsURLConnection.setDefaultHostnameVerifier(NullHostNameVerifier())
        val context = SSLContext.getInstance("TLS")
        context.init(null, arrayOf<X509TrustManager>(NullX509TrustManager()), SecureRandom())
        HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        SocketFactory = context.getSocketFactory();
    }



    private fun initializeInjection(){
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .mTWebServiceModule(MTWebServiceModule())
            .build()
    }

    companion object {
        fun get(context : Context) : MTJudgesApplication = context.applicationContext as MTJudgesApplication;

        fun restartApplication(context : Context) = (context.applicationContext as MTJudgesApplication).initializeInjection()

        var SocketFactory: SSLSocketFactory? = null;
    }


}