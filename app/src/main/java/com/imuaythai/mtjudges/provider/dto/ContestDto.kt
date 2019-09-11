package com.imuaythai.mtjudges.provider.dto

import com.google.gson.annotations.SerializedName

data class ContestDto constructor(
    @SerializedName("id")
    val id: Int,

    @SerializedName("date")
    val date: String,

    @SerializedName("name")
    val name: String
)