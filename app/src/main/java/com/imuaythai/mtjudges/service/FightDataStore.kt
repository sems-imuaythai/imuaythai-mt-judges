package com.imuaythai.mtjudges.service

import com.imuaythai.mtjudges.provider.dto.FightDataDto
import com.imuaythai.mtjudges.provider.dto.FightStatusDto
import com.imuaythai.mtjudges.provider.dto.UserDataDto

interface FightDataStore{

    fun saveFight(fightDataDto: FightDataDto)

    fun saveUserData(userDataDto: UserDataDto)

    fun updateFightStatus(fightStatusDto: FightStatusDto)

}