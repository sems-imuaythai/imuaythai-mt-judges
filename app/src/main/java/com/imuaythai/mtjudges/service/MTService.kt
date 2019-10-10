package com.imuaythai.mtjudges.service

import com.imuaythai.mtjudges.common.exception.RequestException
import com.imuaythai.mtjudges.provider.dto.*

interface MTService {

    @Throws(RequestException::class)
    fun contest(): ContestDto

    @Throws(RequestException::class)
    fun fight(): FightDataDto

    @Throws(RequestException::class)
    fun login(pin: String): UserDataDto

    @Throws(RequestException::class)
    fun sendRoundPoints(points: AddRingFightPointsDto)

    @Throws(RequestException::class)
    fun startRound()

    @Throws(RequestException::class)
    fun pauseFight()

    @Throws(RequestException::class)
    fun resumeFight()

    @Throws(RequestException::class)
    fun resetFight()

    fun sendKnockOutResult(knockOutDto: KnockOutDto)

    @Throws(RequestException::class)
    fun acceptResult()

    fun logout()
}