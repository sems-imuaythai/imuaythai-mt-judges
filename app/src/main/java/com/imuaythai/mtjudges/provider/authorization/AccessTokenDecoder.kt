package com.imuaythai.mtjudges.provider.authorization

import android.util.Base64
import com.google.gson.Gson
import com.imuaythai.mtjudges.provider.dto.AccessTokenDto
import com.imuaythai.mtjudges.provider.dto.UserDataDto
import javax.inject.Inject
import android.util.Log


class AccessTokenDecoder @Inject constructor(){

    fun decodeUserData(accessToken: AccessTokenDto): UserDataDto {

        val tokens = accessToken.accessToken.split(".")

        val jsonString = String(Base64.decode(tokens[1], Base64.DEFAULT), Charsets.UTF_8)

        Log.d("UserData",jsonString)

        return Gson().fromJson(jsonString,UserDataDto::class.java)
    }

}