package com.imuaythai.mtjudges.provider.authorization

import com.imuaythai.mtjudges.provider.dto.ContestDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorizationRepository @Inject constructor(){

    private var authToken: String? = null

    private var contestDto: ContestDto? = null

    fun getAuthToken(): String? = authToken

    fun setAuthToken(authToken: String){
        this.authToken = authToken
    }

    fun getContest(): ContestDto? = contestDto

    fun setContest(contestDto: ContestDto){
        this.contestDto = contestDto
    }

    fun clearSession(){
        authToken = null
        contestDto = null
    }

}