package com.imuaythai.mtjudges.login.service

import com.imuaythai.mtjudges.login.model.LoginData
import com.imuaythai.mtjudges.provider.MTWebService
import io.reactivex.Observable
import javax.inject.Inject


class LoginServiceImpl @Inject constructor(
    var mtWebService: MTWebService
): LoginService {

    override fun login(loginData: LoginData) : Observable<String> {
        return mtWebService.login(hashMapOf(
            "email" to loginData.email,
            "password" to loginData.password
        ))
    }

}