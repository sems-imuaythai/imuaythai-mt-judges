package com.imuaythai.mtjudges.service

import com.imuaythai.mtjudges.provider.dto.FightDataDto
import com.imuaythai.mtjudges.provider.dto.FightStatusDto

interface FightDataStore{

    fun saveFight(fightDataDto: FightDataDto)

    fun updateFightStatus(fightStatusDto: FightStatusDto)

}