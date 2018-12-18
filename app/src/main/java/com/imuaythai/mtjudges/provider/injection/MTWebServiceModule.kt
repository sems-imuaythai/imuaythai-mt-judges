package com.imuaythai.mtjudges.provider.injection

import com.imuaythai.mtjudges.BuildConfig
import com.imuaythai.mtjudges.provider.MTWebService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MTWebServiceModule {

    @Provides @Singleton
    fun provideOkHttpClient() : OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        .build()

    @Provides @Singleton
    fun provideRetrofit(client : OkHttpClient) : Retrofit = Retrofit.Builder()
            .baseUrl("http://ws.audioscrobbler.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides @Singleton
    fun provideMTWebService(retrofit : Retrofit) : MTWebService = retrofit.create(MTWebService::class.java)

}