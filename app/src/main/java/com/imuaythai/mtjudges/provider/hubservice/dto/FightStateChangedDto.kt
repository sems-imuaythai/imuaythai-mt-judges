package com.imuaythai.mtjudges.provider.hubservice.dto

import com.google.gson.annotations.SerializedName

data class FightStateChangedDto(

    @SerializedName("fightId")
    val fightId: Int,

    @SerializedName("time")
    val time: Long,

    @SerializedName("state")
    val state: Int

)