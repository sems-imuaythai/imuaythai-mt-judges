package com.imuaythai.mtjudges.provider.hubservice.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.provider.hubservice.HubService
import com.imuaythai.mtjudges.provider.hubservice.dto.*
import com.microsoft.signalr.Action
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionState
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class HubConnectionServiceImpl @Inject constructor(
    private val hubConnection: HubConnection
) : HubService{

    val connectionState : MutableLiveData<ConnectionState> = MutableLiveData()

    val fightsList : MutableLiveData<List<FightListItem>> = MutableLiveData()

    init {
        //connectionState.value = hubConnection.connectionState
        fightsList.value = ArrayList();

        hubConnection.on("", Action {  })
        hubConnection.onClosed {
            connectionState.value = ConnectionState.DISCONNECTED
        }
    }

    override fun connect(ring: Ring, userId: String, userRole: UserRole) {
        fightsList.value = ArrayList();
        if (hubConnection.connectionState == HubConnectionState.CONNECTED){
            hubConnection.stop()
        }
        var disposable: Disposable = hubConnection.start().subscribe{
            connectionState.value = ConnectionState.CONNECTED
            hubConnection.send(Request.Join.name,
                userId,
                userRole.name
            )
        }
    }

    override fun getFightList(ring: Ring, contestId: Int) = hubConnection.send(
        Request.GetFights.name,
        ring.name,
        contestId
    )

    override fun enterToFight(fightId: Int) = hubConnection.send(
        Request.EnterToFight.name,
        fightId
    )

    override fun pauseFight(fightId: Int) = hubConnection.send(
        Request.PauseFight.name,
        fightId
    )

    override fun resumeFight(fightId: Int) = hubConnection.send(
        Request.ResumeFight.name,
        fightId
    )

    override fun endFight(fightId: Int) = hubConnection.send(
        Request.EndFight.name,
        fightId
    )

    override fun acceptPoints(fightId: Int) = hubConnection.send(
        Request.AcceptPoints.name,
        fightId
    )

    override fun prematureEnd() = hubConnection.send(
        Request.AcceptPoints.name
    )

    override fun startRound(fightId: Int) = hubConnection.send(
        Request.StartRound.name,
        fightId
    )

    override fun endRound(fightId: Int) = hubConnection.send(
        Request.EndRound.name,
        fightId
    )

    override fun provideConnectionState(): LiveData<ConnectionState> = connectionState

    override fun provideFightsList(): LiveData<List<FightListItem>> = fightsList
}