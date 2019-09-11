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

    @SerializedName("redAthleteName")
    val redAthleteName: String,

    @SerializedName("blueAthleteName")
    val blueAthleteName: String
)