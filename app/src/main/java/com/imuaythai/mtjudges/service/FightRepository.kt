package com.imuaythai.mtjudges.service

import com.imuaythai.mtjudges.provider.dto.FightDataDto
import com.imuaythai.mtjudges.provider.dto.FightStatusDto
import com.imuaythai.mtjudges.provider.dto.UserDataDto
import com.imuaythai.mtjudges.provider.dto.UserRole
import io.reactivex.Observable

interface FightRepository {

    fun provideFightStatusObservable(): Observable<FightStatusDto>

    fun getFightData(): FightDataDto?

    fun getUserData(): UserDataDto

    fun getUserRole(): UserRole

}