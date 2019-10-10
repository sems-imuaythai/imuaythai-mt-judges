package com.imuaythai.mtjudges.provider.hubservice.protocol

import com.imuaythai.mtjudges.provider.hubservice.dto.FightStateChangedDto
import com.imuaythai.signalr.*

class HubCommandBinderVisitor constructor(
    val hubCommand: HubCommand,
    val hubConnection: HubConnection
): HubCommandVisitor{

    private lateinit var subscription: Subscription

    override fun visit(action: Action) {
        subscription = hubConnection.on(hubCommand.name,action)
    }

    override fun visit(action1: Action1<String>) {
        subscription = hubConnection.on(hubCommand.name,action1,String::class.java)
    }

    override fun visit(action2: Action2<String, String>) {
        subscription = hubConnection.on(hubCommand.name,action2,
            String::class.java,
            String::class.java
        )
    }

    override fun fightStateChanged(action1: Action1<FightStateChangedDto>) {
        subscription = hubConnection.on(hubCommand.name, action1, FightStateChangedDto::class.java)
    }

    fun subscription() = subscription
}