package com.imuaythai.mtjudges.provider.dto

import com.google.gson.annotations.SerializedName

data class ContestListDto constructor(

    @SerializedName("contests")
    val contests: ArrayList<ContestDto>? = null
)