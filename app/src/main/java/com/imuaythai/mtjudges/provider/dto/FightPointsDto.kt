package com.imuaythai.mtjudges.provider.dto

import com.google.gson.annotations.SerializedName

data class FightPointsDto(

    @SerializedName("fighterId")
    val fighterId: String,

    @SerializedName("c")
    val C: Int,

    @SerializedName("kc")
    val KO: Int,

    @SerializedName("w")
    val W: Int,

    @SerializedName("j")
    val J: Int,

    @SerializedName("x")
    val X: Int,

    @SerializedName("points")
    val points: Int
)