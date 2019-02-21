package com.imuaythai.mtjudges.provider.hubservice.service

interface HubTimeKeeperService : HubStateService {

    fun pauseFight( fightId : Int )

    fun resumeFight( fightId : Int )

    fun endFight( fightId : Int )

}