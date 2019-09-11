package com.imuaythai.mtjudges.provider.dto

import com.google.gson.annotations.SerializedName

data class UserDataDto(

    @SerializedName("jti")
    val jti: String,

    @SerializedName("sub")
    val sub: Int,

    @SerializedName("iat")
    val iat: String,

    @SerializedName("iss")
    val iss: String,

    @SerializedName("given_name")
    val givenName: String,

    @SerializedName("family_name")
    val familyName: String,

    @SerializedName("aud")
    val aud: String
)