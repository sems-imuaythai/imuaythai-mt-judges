package com.imuaythai.mtjudges.login.service

import com.imuaythai.mtjudges.login.model.LoginData
import io.reactivex.Observable

interface LoginService {

    fun login( loginData: LoginData ) : Observable<String>

}