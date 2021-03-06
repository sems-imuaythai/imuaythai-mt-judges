package com.imuaythai.mtjudges.provider.dto

import com.google.gson.annotations.SerializedName

data class AddRingFightPointsDto (

    @SerializedName("redFighterPoints")
    val redFighterPoints: FightPointsDto,

    @SerializedName("blueFighterPoints")
    val blueFighterPoints: FightPointsDto
)