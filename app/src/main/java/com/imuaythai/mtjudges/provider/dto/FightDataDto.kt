package com.imuaythai.mtjudges.provider.dto

data class FightDataDto(
    val weightCategory: String,
    val rounds: Int,
    val roundDuration: Int,
    val breakDuration: Int,
    val redAthleteName: String,
    val blueAthleteName: String
)