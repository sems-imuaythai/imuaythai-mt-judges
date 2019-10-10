package com.imuaythai.mtjudges.provider.dto

import com.google.gson.annotations.SerializedName

data class RoundDto(

    @SerializedName("id")
    val id: Int,

    @SerializedName("status")
    val status: Int

)