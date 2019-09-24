package com.imuaythai.mtjudges.service

import com.imuaythai.mtjudges.provider.dto.FightDataDto
import com.imuaythai.mtjudges.provider.dto.FightStatusDto
import io.reactivex.Observable

interface FightRepository {

    fun provideFightStatusObservable(): Observable<FightStatusDto>

    fun getFightData(): FightDataDto

}