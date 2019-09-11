package com.imuaythai.mtjudges.application.injection

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.application.NullHostNameVerifier
import com.imuaythai.mtjudges.application.NullX509TrustManager
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelFactory
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelKey
import com.imuaythai.mtjudges.application.injection.view.model.EmptyViewModel
import com.imuaythai.mtjudges.main.MainViewModel
import com.imuaythai.mtjudges.settings.model.SettingType
import com.imuaythai.mtjudges.settings.service.SettingsService
import com.imuaythai.mtjudges.settings.service.SettingsServiceImpl
import com.imuaythai.mtjudges.splash.SplashViewModel
import com.imuaythai.mtjudges.test.TestViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import java.security.KeyStore
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import javax.inject.Named
import javax.inject.Singleton
import javax.net.ssl.*

@Module
class ApplicationModule constructor(
    var context : Context
){

    private val sslSocketFactory: SSLSocketFactory

    init {
        val cf = CertificateFactory.getInstance("X.509")
        val caInput = context.resources.openRawResource(R.raw.localhost)
        val ca = cf.generateCertificate(caInput)
        println("ca=" + (ca as X509Certificate).subjectDN)

        val keyStoreType = KeyStore.getDefaultType()
        val keyStore = KeyStore.getInstance(keyStoreType)
        keyStore.load(null, null)
        keyStore.setCertificateEntry("ca", ca)

        val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
        val tmf = TrustManagerFactory.getInstance(tmfAlgorithm)
        tmf.init(keyStore)

        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, tmf.trustManagers, null)
        sslSocketFactory = sslContext.socketFactory
    }

    @Provides
    fun provideApplicationContext() : Context = context;

    @Provides
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory;

    @Provides @IntoMap
    @ViewModelKey(EmptyViewModel::class)
    fun provideEmptyViewModel(): ViewModel = EmptyViewModel();

    @Provides @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(viewModel : MainViewModel): ViewModel = viewModel

    @Provides @IntoMap
    @ViewModelKey(TestViewModel::class)
    fun provideTestViewModel(viewModel : TestViewModel): ViewModel = viewModel

    @Provides @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun provideSplashViewModel(splashViewModel : SplashViewModel): ViewModel = splashViewModel

    @Provides @Singleton
    fun provideSharedPreferences(context: Context) : SharedPreferences = context.getSharedPreferences("com.imuaythai.mtjudges.preferences", 0);

    @Provides @Singleton
    fun provideSettingsService(service: SettingsServiceImpl) : SettingsService = service

    @Provides @Named("API_HOST")
    fun provideConfigurationApiHost(settingsService: SettingsService) : String
            = settingsService.provideSettingsItem(SettingType.API_HOST).value!!.value

    @Provides @Named("SELECTED_RING")
    fun provideConfigurationSelectedRing(settingsService: SettingsService) : String
            = settingsService.provideSettingsItem(SettingType.SELECTED_RING).value!!.value

    @Provides @Singleton
    fun provideSSLSocketFactory(context: Context): SSLSocketFactory = sslSocketFactory

    @Provides @Singleton
    fun provideHostnameVerifier(): HostnameVerifier = NullHostNameVerifier()

    @Provides @Singleton
    fun provideTrustManager(): X509TrustManager = NullX509TrustManager()



}