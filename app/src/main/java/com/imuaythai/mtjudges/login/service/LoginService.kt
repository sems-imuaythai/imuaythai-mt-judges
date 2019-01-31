package com.imuaythai.mtjudges.login.service

import com.imuaythai.mtjudges.login.model.LoginData
import com.imuaythai.mtjudges.provider.dto.LoginResponse
import io.reactivex.Observable

interface LoginService {

    fun login( loginData: LoginData ) : Observable<LoginResponse>

}