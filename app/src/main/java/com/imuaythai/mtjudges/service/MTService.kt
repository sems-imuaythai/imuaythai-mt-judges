package com.imuaythai.mtjudges.service

import com.imuaythai.mtjudges.common.exception.RequestException
import com.imuaythai.mtjudges.provider.dto.*

interface MTService {

    @Throws(RequestException::class)
    fun initialize(ring: Ring): FightDataDto

    @Throws(RequestException::class)
    fun login(pin: String): UserDataDto

    fun sendRoundPoints(points: PointCardDto)

    fun sendKnockOutResult(knockOutDto: KnockOutDto)

    fun commitResult()
}