package com.imuaythai.mtjudges.provider.hubservice.service

import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.BuildConfig
import com.imuaythai.mtjudges.common.exception.ConnectionException
import com.imuaythai.mtjudges.provider.authorization.AuthorizationRepository
import com.imuaythai.mtjudges.provider.hubservice.HubService
import com.imuaythai.mtjudges.provider.hubservice.dto.*
import com.imuaythai.mtjudges.provider.hubservice.protocol.HubCommand
import com.imuaythai.mtjudges.provider.hubservice.protocol.HubCommandBinderVisitor
import com.imuaythai.mtjudges.provider.hubservice.protocol.HubCommandHandler
import com.imuaythai.mtjudges.provider.hubservice.protocol.HubMethod
import com.imuaythai.signalr.*
import dagger.Lazy
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

class SignalRHubService @Inject constructor(
    @Named("SELECTED_RING") val ringName: String,
    @Named("API_HOST") val apiHost: String,
    private val hostnameVerifier: Lazy<HostnameVerifier>,
    private val socketFactory: Lazy<SSLSocketFactory>,
    private val trustManager: Lazy<X509TrustManager>,
    private val authorizationRepository: AuthorizationRepository,
    private val commandHandlers: MutableMap<HubCommand, Provider<HubCommandHandler>>
): HubService{

    private val connectionState : MutableLiveData<ConnectionState> = MutableLiveData()

    private val subscriptionList: ArrayList<Subscription> = ArrayList()

    private var hubConnection: HubConnection? = null

    init {
        connectionState.postValue(ConnectionState.DISCONNECTED)
        connectionState.observeForever { state ->
            if(state!=ConnectionState.CONNECTED) {
                unbindHandlers()
            } else {
                bindHandlers()
            }
        }
    }

    @Synchronized
    override fun connect(){
        if(hubConnection?.connectionState == HubConnectionState.CONNECTED ){
            return
        }

        val connection = HubConnectionBuilder
            .create("$apiHost/rings/$ringName")
            .withHttpClient(DefaultHttpClient(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                })
                .hostnameVerifier(hostnameVerifier.get())
                .sslSocketFactory(socketFactory.get(),trustManager.get())))
            .withAccessTokenProvider(authorizationRepository.provideAuthToken())
            .build()

        try {
            connectionState.postValue(ConnectionState.CONNECTING)
            connection.start().blockingAwait()
            if(connection.connectionState == HubConnectionState.CONNECTED){
                hubConnection = connection
                connectionState.postValue(ConnectionState.CONNECTED)
                return
            }else{
                connectionState.postValue(ConnectionState.DISCONNECTED)
                throw ConnectionException()
            }

        }catch (throwable: Throwable){
            connectionState.postValue(ConnectionState.DISCONNECTED)
            throw ConnectionException()
        }
    }

    private fun bindHandlers(){
        commandHandlers.forEach { entry ->
            val binder = HubCommandBinderVisitor(entry.key,hubConnection!!)
            entry.value.get().accept(binder)
            subscriptionList.add(binder.subscription())
        }
    }

    private fun unbindHandlers(){
        subscriptionList.forEach {subscription ->
            subscription.unsubscribe()
        }
        subscriptionList.clear()
    }

    override fun connectionState() = connectionState

    override fun join(fightId: Int) {
        hubConnection?.send(HubMethod.Join.name,fightId)
    }

}