package com.imuaythai.mtjudges.provider.hubservice.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.BuildConfig
import com.imuaythai.mtjudges.common.exception.ConnectionException
import com.imuaythai.mtjudges.provider.hubservice.HubService
import com.imuaythai.mtjudges.provider.hubservice.dto.*
import com.imuaythai.signalr.DefaultHttpClient
import com.imuaythai.signalr.HubConnection
import com.imuaythai.signalr.HubConnectionBuilder
import com.imuaythai.signalr.HubConnectionState
import dagger.Lazy
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Named
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

class SignalRHubService @Inject constructor(
    @Named("SELECTED_RING") val ringName: String,
    private val hostnameVerifier: Lazy<HostnameVerifier>,
    private val socketFactory: Lazy<SSLSocketFactory>,
    private val trustManager: Lazy<X509TrustManager>
): HubService{

    private val connectionState : MutableLiveData<ConnectionState> = MutableLiveData()

    private var hubConnection: HubConnection? = null

    init {
        connectionState.postValue(ConnectionState.DISCONNECTED)
    }

    @Synchronized
    override fun connect(): LiveData<ConnectionState> {
        if(hubConnection?.connectionState == HubConnectionState.CONNECTED ){
            return connectionState
        }

        val connection = HubConnectionBuilder
            .create("https://10.0.2.2:5001/ring/$ringName")
            .withHttpClient(DefaultHttpClient(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                })
                .hostnameVerifier(hostnameVerifier.get())
                .sslSocketFactory(socketFactory.get(),trustManager.get())))
            .build()

        try {
            connectionState.postValue(ConnectionState.CONNECTING)
            connection.start().blockingAwait()
            if(connection.connectionState == HubConnectionState.CONNECTED){
                connectionState.postValue(ConnectionState.CONNECTED)
                hubConnection = connection
                return connectionState
            }else{
                connectionState.postValue(ConnectionState.DISCONNECTED)
                throw ConnectionException()
            }

        }catch (throwable: Throwable){
            connectionState.postValue(ConnectionState.DISCONNECTED)
            throw ConnectionException()
        }
    }

}