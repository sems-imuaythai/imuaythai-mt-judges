package com.imuaythai.mtjudges.provider.dto

import com.google.gson.annotations.SerializedName

data class FightDataDto(
    @SerializedName("weightCategory")
    val weightCategory: String,

    @SerializedName("rounds")
    val rounds: Int,

    @SerializedName("roundDuration")
    val roundDuration: Int,

    @SerializedName("breakDuration")
    val breakDuration: Int,

    @SerializedName("redFighterId")
    val redFighterId: Int,

    @SerializedName("redFighterName")
    val redFighterName: String,

    @SerializedName("blueFighterId")
    val blueFighterId: Int,

    @SerializedName("blueFighterName")
    val blueFighterName: String
)