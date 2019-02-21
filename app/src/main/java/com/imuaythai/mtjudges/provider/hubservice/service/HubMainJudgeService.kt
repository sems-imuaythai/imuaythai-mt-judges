package com.imuaythai.mtjudges.provider.hubservice.service

interface HubMainJudgeService : HubStateService {

    fun acceptPoints(fightId : Int /*, Guid judgePointsId*/)

    fun prematureEnd()

    fun startRound( fightId : Int )

    fun endRound( fightId : Int )

}