package com.imuaythai.mtjudges.provider.hubservice.protocol

import com.imuaythai.mtjudges.provider.hubservice.dto.FightStateChangedDto
import com.imuaythai.signalr.Action
import com.imuaythai.signalr.Action1
import com.imuaythai.signalr.Action2

interface HubCommandVisitor {

    fun visit(action: Action)

    fun visit(action1: Action1<String>)

    fun fightStateChanged(action1: Action1<FightStateChangedDto>)

    fun visit(action2: Action2<String,String>)

}