package com.imuaythai.mtjudges.provider.webservice.injection

import com.imuaythai.mtjudges.provider.MTWebService
import com.imuaythai.mtjudges.provider.webservice.WebService
import com.imuaythai.mtjudges.provider.webservice.interceptor.RequestInterceptor
import com.imuaythai.mtjudges.service.MTService
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor

@Module
class WebServiceModule {

    @Provides @Singleton
    fun provideWebService(@Named("API_HOST") apiHost : String,
                          requestInterceptor: RequestInterceptor): WebService {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient().newBuilder()
            .connectionSpecs(listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
            .addInterceptor(requestInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(apiHost)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(WebService::class.java);
    }

    @Provides @Singleton
    fun provideMTService(service :MTWebService) : MTService = service

}