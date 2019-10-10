package com.imuaythai.mtjudges.provider.dto

import com.google.gson.annotations.SerializedName

data class FightDataDto(

    @SerializedName("fightId")
    val fightId: Int,

    @SerializedName("weightCategory")
    val weightCategory: String,

    @SerializedName("rounds")
    val rounds: List<RoundDto>,

    @SerializedName("roundDuration")
    val roundDuration: Int,

    @SerializedName("breakDuration")
    val breakDuration: Int,

    @SerializedName("redFighterId")
    val redFighterId: String,

    @SerializedName("redFighterName")
    val redFighterName: String,

    @SerializedName("blueFighterId")
    val blueFighterId: String,

    @SerializedName("blueFighterName")
    val blueFighterName: String
)