package com.imuaythai.mtjudges.provider.hubservice.handler

import android.os.Handler
import android.os.Looper
import com.imuaythai.mtjudges.provider.dto.FightState
import com.imuaythai.mtjudges.provider.dto.FightStatusDto
import com.imuaythai.mtjudges.provider.hubservice.dto.FightStateChangedDto
import com.imuaythai.mtjudges.provider.hubservice.protocol.HubCommandHandler
import com.imuaythai.mtjudges.provider.hubservice.protocol.HubCommandVisitor
import com.imuaythai.mtjudges.service.FightDataStore
import com.imuaythai.signalr.Action1
import javax.inject.Inject

class FightStateChangedHandler @Inject constructor(
    private val fightDataStore: FightDataStore
): HubCommandHandler, Action1<FightStateChangedDto>{

    val handler: Handler = Handler(Looper.getMainLooper())

    override fun invoke(fightStateChangedDto: FightStateChangedDto) {
        val fightState = FightState.fromStateId(fightStateChangedDto.state)
        handler.post{
            fightDataStore.updateFightStatus(FightStatusDto(fightState,fightStateChangedDto.time*1000))
        }
    }

    override fun accept(visitor: HubCommandVisitor) {
        visitor.fightStateChanged(this)
    }
}