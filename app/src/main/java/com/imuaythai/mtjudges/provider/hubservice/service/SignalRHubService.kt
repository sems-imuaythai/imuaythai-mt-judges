package com.imuaythai.mtjudges.provider.hubservice.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.application.MTJudgesApplication
import com.imuaythai.mtjudges.application.NullHostNameVerifier
import com.imuaythai.mtjudges.application.NullX509TrustManager
import com.imuaythai.mtjudges.provider.hubservice.HubService
import com.imuaythai.mtjudges.provider.hubservice.dto.*
import com.imuaythai.signalr.DefaultHttpClient
import com.imuaythai.signalr.HubConnection
import com.imuaythai.signalr.HubConnectionBuilder
import com.imuaythai.signalr.HubConnectionState
import io.reactivex.disposables.Disposable
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Named

class SignalRHubService @Inject constructor(
    @Named("SELECTED_RING") ringName: String
): HubService{

    private val connectionState : MutableLiveData<ConnectionState> = MutableLiveData()

    private val hubConnection: HubConnection = HubConnectionBuilder
        .create("https://10.0.2.2:5001/ring/$ringName")
        .withHttpClient(DefaultHttpClient(OkHttpClient.Builder()
            .hostnameVerifier(NullHostNameVerifier())
            .sslSocketFactory(MTJudgesApplication.SocketFactory!!,NullX509TrustManager())))
        .build()

    init {
        connectionState.postValue(ConnectionState.CONNECTING)
        hubConnection.on("ReceiveMessage",{ name, message ->
            Log.i("testConnect", "[$name]: $message" )
        },String::class.java,String::class.java)
    }

    override fun connect(): LiveData<ConnectionState> {
        if(hubConnection.connectionState == HubConnectionState.DISCONNECTED){
            connectionState.postValue(ConnectionState.CONNECTING)
            var disposable: Disposable = hubConnection.start().subscribe({
                connectionState.postValue(ConnectionState.CONNECTED)
                hubConnection.send("SendMessage","Seweryn", "Hello World!")
            },{ throwable ->
                Log.e("testConnect", "error= ${throwable.message}" )
                connectionState.postValue(ConnectionState.DISCONNECTED)
            })
        }
        return connectionState;
    }

    /*override fun getFightList(ring: Ring, contestId: Int){}

    override fun enterToFight(fightId: Int){}

    override fun pauseFight(fightId: Int){}

    override fun resumeFight(fightId: Int){}

    override fun endFight(fightId: Int){}

    override fun acceptPoints(fightId: Int){}

    override fun prematureEnd(){}

    override fun startRound(fightId: Int){}

    override fun endRound(fightId: Int){}

    override fun provideConnectionState(): LiveData<ConnectionState> = connectionState

    override fun provideFightsList(): LiveData<List<FightListItem>> = fightsList*/
}