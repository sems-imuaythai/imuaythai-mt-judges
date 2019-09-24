package com.imuaythai.mtjudges.provider.dto

data class FightStatusDto(
    val roundNum: Int,
    val state: FightState,
    val time: Int
)